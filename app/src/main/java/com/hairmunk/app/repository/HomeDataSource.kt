package com.hairmunk.app.repository

import com.hairmunk.app.model.HomeData

interface HomeDataSource {

    fun getHomeData(): HomeData?
}