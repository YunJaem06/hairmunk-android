package com.hairmunk.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.hairmunk.app.R
import com.hairmunk.app.databinding.FragmentAuthBinding

class AuthFragment: Fragment() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
        mAuth = FirebaseAuth.getInstance()


    }
}