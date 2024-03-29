package com.hairmunk.app.ui.map

import com.hairmunk.app.model.ResultSearchKeyword
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoAPI {
    @GET("v2/local/search/keyword.json")
    fun getSearchKeyword(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("x") x: Double?,
        @Query("y") y: Double?,
        @Query("page") page: Int
    ): Call<ResultSearchKeyword>
}