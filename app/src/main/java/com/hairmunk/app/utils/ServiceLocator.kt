package com.hairmunk.app.utils

import android.content.Context
import androidx.room.Room
import com.hairmunk.app.database.AppDatabase
import com.hairmunk.app.network.ApiClient
import com.hairmunk.app.repository.cart.CartItemLocalDataSource
import com.hairmunk.app.repository.cart.CartRepository

object ServiceLocator {

    private var apiClient: ApiClient? = null
    private var database: AppDatabase? = null
    private var cartRepository: CartRepository? = null

    fun provideApiClient(): ApiClient {
        return apiClient ?: kotlin.run {
            ApiClient.create().also {
                apiClient = it
            }
        }
    }

    private fun provideDatabase(applicationContext: Context): AppDatabase {
        return database ?: kotlin.run {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "hairmunk"
            ).build().also {
                database = it
            }
        }
    }

    fun provideCartRepository(context: Context): CartRepository {
        return cartRepository ?: kotlin.run {
            val dao = provideDatabase(context.applicationContext).cartItemDao()
            CartRepository(CartItemLocalDataSource(dao)).also {
                cartRepository = it
            }
        }
    }
}