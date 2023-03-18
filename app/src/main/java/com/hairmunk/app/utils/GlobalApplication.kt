package com.hairmunk.app.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "d8ae330b057065d6d09df556554d4bc8")
    }
}