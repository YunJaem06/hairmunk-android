package com.hairmunk.app.ui


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hairmunk.app.R
import com.hairmunk.app.SharedPreferences
import com.hairmunk.app.databinding.FragmentMypageBinding
import com.hairmunk.app.ui.login.LOGIN
import com.hairmunk.app.ui.login.LoginActivity
import com.kakao.sdk.user.UserApiClient
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback


class MyPageFragment : Fragment() {

    private lateinit var binding : FragmentMypageBinding
    private var name: String? = null
    private var email: String? = null
    private var login: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        val bundle = arguments
        if (bundle != null) {
            name = bundle.getString("name")
            email = bundle.getString("email")
            login = SharedPreferences.getStrValue(activity, LOGIN, "none")
        }

        if (login == "kakao") {
            binding.tvMypageLogout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.kakao_yellow))
        } else if (login == "naver"){
            binding.tvMypageLogout.text = "네이버 로그아웃"
            binding.tvMypageLogout.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.tvMypageLogout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.naver_green))
            binding.ivMypageIcon.setImageResource(R.drawable.icon_start_naver)
        }

        binding.tvMypageName.text = name
        binding.tvMypageEmail.text = email

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvMypageLogout.setOnClickListener {
            if (login == "naver") {
                NaverIdLoginSDK.logout()
                NidOAuthLogin().callDeleteTokenApi(requireContext(), object : OAuthLoginCallback {
                    override fun onError(errorCode: Int, message: String) {
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                    }

                    override fun onSuccess() {
                        Toast.makeText(context, "네이버 아이디 토큰삭제 성공!", Toast.LENGTH_SHORT).show()                    }

                })
            } else if (login == "kakao") {
                UserApiClient.instance.logout { error ->
                    if (error != null) {
                        Toast.makeText(context, "로그아웃 실패. SDK에서 토큰 삭제됨: $error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "로그아웃 성공. SDK에서 토큰 삭제됨", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            SharedPreferences.putStrValue(activity, LOGIN, "none")
            activity?.finish()
        }
    }


}