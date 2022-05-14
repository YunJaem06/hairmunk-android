package com.hairmunk.app.repository.home

import com.hairmunk.app.model.HomeData

interface HomeDataSource {

    fun getHomeData(): HomeData?
}