package com.hairmunk.app.network

import com.hairmunk.app.model.Category
import com.hairmunk.app.model.CategoryDetail
import com.hairmunk.app.model.Product
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("categories.json")
    suspend fun getCategories() : List<Category>

    @GET("{categoryId}.json")
    suspend fun getCategoryDetail(@Path("categoryId") categoryId: String): CategoryDetail
    @GET("products/{productId}.json")
    suspend fun getProductDetail(@Path("productId") productId: String): Product

    companion object {

        private const val baseUrl = "https://hairmunk-default-rtdb.asia-southeast1.firebasedatabase.app/"

        fun create(): ApiClient {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}