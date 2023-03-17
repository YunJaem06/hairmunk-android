package com.hairmunk.app

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes private val layoutId: Int): Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        init()
    }

    fun showCustomToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    // 앱을 전체 화면
    fun Activity.setStatusBarTransparent() {
        window.apply {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        if(Build.VERSION.SDK_INT >= 30) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    // 상태창 높이 계산
    fun Context.statusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
        else 0
    }

    fun Context.navigationHeight(): Int {
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")

        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
        else 0
    }

    // 전체화면 취소
    fun Activity.setStatusBarOrigin() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        if(Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
            WindowCompat.setDecorFitsSystemWindows(window, true)
        }
    }

    protected abstract fun init()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}