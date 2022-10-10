package com.hairmunk.app.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hairmunk.app.databinding.FragmentMypageBinding


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
        return binding.root
    }


}