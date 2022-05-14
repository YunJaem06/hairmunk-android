package com.hairmunk.app.repository.home

import com.hairmunk.app.model.HomeData

class HomeRepository(private val assetDataSource: HomeAssetDataSource) {

    fun getHomeData() : HomeData? {
        return assetDataSource.getHomeData()
    }
}