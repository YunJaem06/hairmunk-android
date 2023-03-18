package com.hairmunk.app.ui

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.hairmunk.app.utils.NaverProfile
import com.hairmunk.app.R
import com.hairmunk.app.utils.SharedPreferences
import com.hairmunk.app.databinding.ActivityMainBinding
import com.hairmunk.app.model.NaverResult
import com.hairmunk.app.ui.login.LOGIN
import com.hairmunk.app.ui.login.LoginActivity
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var name: String? = null
    private var email: String? = null
    private var img: String? = null
    private var id: Long = 0

    private var bundle = Bundle(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment(SharedPreferences.getStrValue(this, LOGIN, "none"))

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        bottomNavigationView.itemIconTintList = null

        val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navController?.let {
            bottomNavigationView.setupWithNavController(it)
            val fragment = MyPageFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.navigation_sign, fragment)
                commit()
            }
        }
    }

    private fun initFragment(login : String?) {
        if (login == "naver") {
            initNaver()
        } else if (login == "kakao") {
            initKakao()
        }
    }

    private fun initNaver() {
        Thread{
            NaverProfile.setToken(LoginActivity.naverAccessToken)
            val response = NaverProfile.getProfile()
            val result = Gson().fromJson(response, NaverResult::class.java)

            name = result.response.name
            email = result.response.email
            img = result.response.img

            id = 0L
            bundle.putString("email", email)
            bundle.putString("name", name)
            bundle.putString("img",img)
            bundle.putString("id", id.toString())

        }.start()
    }

    private fun initKakao() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(ContentValues.TAG, "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(ContentValues.TAG, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

                name = user.kakaoAccount?.profile?.nickname
                email = user.kakaoAccount?.email
                img = user.kakaoAccount?.profile?.thumbnailImageUrl
                id = user.id!!

                bundle.putString("email", email)
                bundle.putString("name", name)
                bundle.putString("img",img)
                bundle.putString("id", id.toString())

            }
        }
    }
}