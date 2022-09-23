package com.hairmunk.app.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hairmunk.app.R
import com.hairmunk.app.databinding.ActivitySignupBinding
import com.hairmunk.app.model.LoginAgency
import com.hairmunk.app.model.LoginAgree

class SignupActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignupBinding

    private lateinit var agencyAdapter: AgencyRvAdapter
    private lateinit var agencyDialog: BottomSheetDialog

//    private lateinit var agreeAdapter: LoginAgreeRvAdapter
    private lateinit var agreeDialog: BottomSheetDialog

    var agencyList = ArrayList<LoginAgency>()
    var agreeList = ArrayList<LoginAgree>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginNext.setOnClickListener(this)

        // 뒤로가기
        binding.ivPhoneBack.setOnClickListener {
            finish()
        }
        
        //통신사
        setAgency()

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


    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}