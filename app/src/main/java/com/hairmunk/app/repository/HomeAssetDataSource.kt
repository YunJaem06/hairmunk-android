package com.hairmunk.app.repository

import com.google.gson.Gson
import com.hairmunk.app.AssetLoader
import com.hairmunk.app.model.HomeData

class HomeAssetDataSource(private val assetLoader: AssetLoader) : HomeDataSource {

    private val gson = Gson()

    override fun getHomeData(): HomeData? {
        return assetLoader.getJsonString("home.json")?.let { homeJsonString->
            return gson.fromJson(homeJsonString, HomeData::class.java)
        }
    }
}