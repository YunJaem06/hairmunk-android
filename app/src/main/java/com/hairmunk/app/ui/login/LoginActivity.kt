package com.hairmunk.app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hairmunk.app.R
import com.hairmunk.app.SharedPreferences
import com.hairmunk.app.databinding.ActivityLoginBinding
import com.hairmunk.app.ui.MainActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

const val LOGIN = "login"

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var otherDialog: BottomSheetDialog

    // 카카오 로그인 callback
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d("login-fail", "카카오계정으로 로그인 실패 : $error")
        }
        else if (token != null) {
            UserApiClient.instance.me { user, error ->
                Log.d("login-success ", "카카오계정으로 로그인 성공 : ${token.accessToken}")

                SharedPreferences.putStrValue(this@LoginActivity, LOGIN, "kakao")

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val naverClientId = getString(R.string.naver_client_id)
        val naverClientSecret = getString(R.string.naver_client_secret)
        val naverClientName = getString(R.string.naver_client_name)

        NaverIdLoginSDK.initialize(this, naverClientId, naverClientSecret, naverClientName)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        // 다른 로그인 바텀시트
        otherLogin()

        binding.ivLoginKakao.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Log.d("login-fail", "카카오계정으로 로그인 실패 : $error")
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        Log.d("login-success ", "카카오계정으로 로그인 성공 : ${token.accessToken}")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun otherLogin() {

        val otherView = layoutInflater.inflate(R.layout.dialog_login_other_sheet, null)
        otherDialog = BottomSheetDialog(this)
        otherDialog.setContentView(otherView)

        binding.tvLoginOther.setOnClickListener {
            otherDialog.show()

            // 회원가입 화면이동
            otherView.findViewById<LinearLayout>(R.id.ll_login_phone).setOnClickListener {
                var intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
                otherDialog.dismiss()
            }
            otherView.findViewById<LinearLayout>(R.id.ll_login_naver).setOnClickListener {
                startNaverLogin()
            }
            otherView.findViewById<LinearLayout>(R.id.ll_login_facebook).setOnClickListener{
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun startNaverLogin(){
        var naverToken : String = ""

        val profileCallback = object : NidProfileCallback<NidProfileResponse>{
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Toast.makeText(this@LoginActivity, "errorCode: ${errorCode}\n" +
                        "errorDescription: $errorDescription", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess(result: NidProfileResponse) {
                val userId = result.profile?.id
                val nickname = result.profile?.nickname

                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("login", "naver")
                intent.putExtra("profileid", userId)
                intent.putExtra("profilename", nickname)
                startActivity(intent)
                finish()

            }

        }

        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Toast.makeText(this@LoginActivity, "errorCode: ${errorCode}\n" +
                        "errorDescription: $errorDescription", Toast.LENGTH_SHORT).show()
            }

            override fun onSuccess() {
                naverToken = NaverIdLoginSDK.getAccessToken().toString()
                SharedPreferences.putStrValue(this@LoginActivity, LOGIN, "naver")

                // 로그인 유저 정보
                NidOAuthLogin().callProfileApi(profileCallback)

            }
        }
        NaverIdLoginSDK.authenticate(this, oauthLoginCallback)
    }

    override fun onStart() {
        super.onStart()
        // 자동 로그인
        val lastLogin = SharedPreferences.getStrValue(this, LOGIN, "none")
        if (lastLogin == "kakao") {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        } else if (lastLogin == "naver") {
            // 네이버 자동로그인 필요
        }
    }
}