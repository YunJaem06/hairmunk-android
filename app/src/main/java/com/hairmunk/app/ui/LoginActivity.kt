package com.hairmunk.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hairmunk.app.R
import com.hairmunk.app.databinding.ActivityLoginBinding
import com.kakao.sdk.common.util.Utility

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var otherDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)
        
        // 다른 로그인 바텀시트
        otherLogin()

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


        }

    }

}