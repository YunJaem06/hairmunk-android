package com.hairmunk.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hairmunk.app.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private lateinit var Auth: FirebaseAuth
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Auth = Firebase.auth
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root

    }
    // 로그아웃 구현
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
            Auth.signOut()
        }
        binding.tvAuth.text = "안녕하세요 ${Auth.currentUser?.email} 님"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}