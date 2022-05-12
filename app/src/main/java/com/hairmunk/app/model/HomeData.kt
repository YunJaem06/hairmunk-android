package com.hairmunk.app.model

import com.google.gson.annotations.SerializedName
import com.hairmunk.app.model.Banner

data class HomeData(
    val title: Title,
    @SerializedName("top_banners") val topBanners: List<Banner>
)
