package com.hairmunk.app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hairmunk.app.R
import com.hairmunk.app.databinding.ActivitySignupBinding
import com.hairmunk.app.model.LoginAgency
import com.hairmunk.app.model.LoginAgree
import com.hairmunk.app.ui.MainActivity
import org.w3c.dom.Text

class SignupActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var agencyAdapter: AgencyRvAdapter
    private lateinit var agencyDialog: BottomSheetDialog

    private lateinit var agreeAdapter: AgreeRvAdapter
    private lateinit var agreeDialog: BottomSheetDialog

    var agencyList = ArrayList<LoginAgency>()
    var agreeList = ArrayList<LoginAgree>()

    private var email = false
    private var password = false
    private var agency = false
    private var agree = false
    private var phone = false
    private var allclick = false
    private var verification = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginNext.setOnClickListener(this)

        // 뒤로가기
        binding.ivPhoneBack.setOnClickListener {
            finish()
        }
        
        // 통신사
        setAgency()

        // 이메일
        putEmail()

        //동의
        setAgree()

    }
    private fun setAgency() {
        agencyList.add(LoginAgency("SKT", R.drawable.icon_radio_unclick, false))
        agencyList.add(LoginAgency("KT", R.drawable.icon_radio_unclick, false))
        agencyList.add(LoginAgency("LG U+", R.drawable.icon_radio_unclick, false))
        agencyList.add(LoginAgency("SKT 알뜰폰", R.drawable.icon_radio_unclick, false))
        agencyList.add(LoginAgency("KT 알뜰폰", R.drawable.icon_radio_unclick, false))
        agencyList.add(LoginAgency("LG U+ 알뜰폰", R.drawable.icon_radio_unclick, false))

        val agencyView = layoutInflater.inflate(R.layout.dialog_agency, null)
        agencyDialog = BottomSheetDialog(this)
        agencyDialog.setContentView(agencyView)

        agencyAdapter = AgencyRvAdapter(this, agencyList)

        agencyAdapter.setAgencyItem(agencyList)

        val agencyRv = agencyView.findViewById<RecyclerView>(R.id.rv_agency)
        agencyRv.adapter = agencyAdapter

        binding.llLoginPhoneAgency.setOnClickListener {
            agencyDialog.show()
        }

        agencyAdapter.clickListener(object : AgencyRvAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                binding.tvLoginPhoneAgency.text = agencyAdapter.itemList[position].title
                for (i in 0 until agencyList.size) {
                    agencyAdapter.itemList[i].click = false
                }
                agencyAdapter.itemList[position].click = true
                agencyAdapter.notifyDataSetChanged()
                agencyDialog.dismiss()
                agency = true
                binding.btnLoginNext.isEnabled = true
                putPhoneNumber()
            }

        })


    }
    private fun setAgree() {
        agreeList.add(LoginAgree("헤어뭉크 이용약관 (필수)", false))
        agreeList.add(LoginAgree("개인정보 수집 이용 동의 (필수)", false))
        agreeList.add(LoginAgree("휴대폰 본인확인서비스 (필수)", false))
        agreeList.add(LoginAgree("휴면시 개인정보 분리보관 동의 (필수)", false))
        agreeList.add(LoginAgree("위치정보 이용약관 동의 (필수)", false))
        agreeList.add(LoginAgree("개인정보 수집 이용 동의 (선택)", false))
        agreeList.add(LoginAgree("마케팅 수신 동의 (선택)", false))
        agreeList.add(LoginAgree("개인정보 광고활용 동의 (선택)", false))

        val agreeView = layoutInflater.inflate(R.layout.dialog_agreement, null)
        agreeDialog = BottomSheetDialog(this)
        agreeDialog.setContentView(agreeView)

        agreeAdapter = AgreeRvAdapter(this, agreeList)

        agreeAdapter.setAgreeItem(agreeList)

        val agreeRv = agreeView.findViewById<RecyclerView>(R.id.rv_agree_list)
        agreeRv.adapter = agreeAdapter

        agreeAdapter.clickListener(object : AgreeRvAdapter.OnItemClickListener{
            override fun onClick(view: View, position: Int) {
                agreeAdapter.agreeList[position].isCheck = !agreeAdapter.agreeList[position].isCheck
                agreeAdapter.notifyDataSetChanged()
                if (agreeAdapter.agreeList[0].isCheck && agreeAdapter.agreeList[1].isCheck && agreeAdapter.agreeList[2].isCheck &&
                    agreeAdapter.agreeList[3].isCheck && agreeAdapter.agreeList[4].isCheck) {

                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).isClickable = true
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).setOnClickListener {
                        agree = true
                        agreeDialog.dismiss()
                    }
                } else {
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).isClickable = false
                }
            }
        })
        agreeView.findViewById<ConstraintLayout>(R.id.cl_dialog_all_agree).setOnClickListener {
            if (allclick){
                agreeView.findViewById<ImageView>(R.id.iv_dialog_all_check).setImageResource(R.drawable.icon_all_unagree)
                for (i in 0 until agreeList.size){
                    agreeAdapter.agreeList[i].isCheck = false
                }
                if (agreeAdapter.agreeList[0].isCheck && agreeAdapter.agreeList[1].isCheck && agreeAdapter.agreeList[2].isCheck &&
                    agreeAdapter.agreeList[3].isCheck && agreeAdapter.agreeList[4].isCheck) {

                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).isClickable = true
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).setOnClickListener {
                        agree = true
                        agreeDialog.dismiss()
                    }
                }
                agreeAdapter.notifyDataSetChanged()
                allclick = false
            } else {
                agreeView.findViewById<ImageView>(R.id.iv_dialog_all_check).setImageResource(R.drawable.icon_all_agree)
                for (i in 0 until agreeList.size){
                    agreeAdapter.agreeList[i].isCheck = true
                }
                if (agreeAdapter.agreeList[0].isCheck && agreeAdapter.agreeList[1].isCheck && agreeAdapter.agreeList[2].isCheck &&
                    agreeAdapter.agreeList[3].isCheck && agreeAdapter.agreeList[4].isCheck) {

                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).isClickable = true
                    agreeView.findViewById<AppCompatButton>(R.id.btn_dialog_next).setOnClickListener {
                        agree = true
                        agreeDialog.dismiss()
                    }
                }
                agreeAdapter.notifyDataSetChanged()
                allclick = true
            }
        }
    }


    // 이메일
    private fun putEmail() {
        binding.etLoginEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnLoginNext.isEnabled = binding.etLoginEmail.length() >= 1
                email = true
            }

        })
    }
    private fun putPassword() {
        binding.etLoginPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnLoginNext.isEnabled = binding.etLoginPassword.length() >= 4
                password = true
            }

        })

    }

    private fun putPhoneNumber() {
        binding.etLoginPhoneNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnLoginNext.isEnabled = binding.etLoginPhoneNumber.length() > 10
                phone = true
            }

        })

    }

    private fun putVerification() {
        binding.etLoginVerification.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnLoginNext.isEnabled = binding.etLoginVerification.length() > 3
                verification = true
            }

        })
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.btn_login_next -> {
                if (email && !password) {
                    binding.llLoginPassword.visibility = View.VISIBLE
                    binding.etLoginPassword.requestFocus()
                    binding.tvLoginTitle.text = "비밀번호\n입력해주세요"
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    putPassword()
                }
                if (password && !agency) {
                    binding.llLoginPhoneAgency.visibility = View.VISIBLE
                    binding.tvLoginTitle.text = "통신사\n선택해주세요"
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    agencyDialog.show()
                }
                if (agency && !phone){
                    binding.llLoginPhoneNumber.visibility = View.VISIBLE
                    binding.etLoginPhoneNumber.requestFocus()
                    binding.tvLoginTitle.text = "전화번호\n입력해주세요"
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    putPhoneNumber()
                }
                if (phone && !agree){
                    agreeDialog.show()
                }
                if(agree && !verification){
                    binding.llLoginEmail.visibility = View.GONE
                    binding.llLoginPassword.visibility = View.GONE
                    binding.llLoginPhoneAgency.visibility = View.GONE
                    binding.llLoginPhoneNumber.visibility = View.GONE
                    binding.llLoginVerification.visibility = View.VISIBLE

                    binding.etLoginVerification.requestFocus()
                    binding.tvLoginTitle.text = "인증번호\n입력해주세요"
                    binding.btnLoginNext.text = "확인"
                    binding.btnLoginNext.isEnabled = false
                    putVerification()
                }
                if (agree && verification){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }

        }
    }

}