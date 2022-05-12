package com.hairmunk.app.repository

import com.hairmunk.app.model.HomeData

class HomeRepository(private val assetDataSource: HomeAssetDataSource) {

    fun getHomeData() : HomeData? {
        return assetDataSource.getHomeData()
    }
}