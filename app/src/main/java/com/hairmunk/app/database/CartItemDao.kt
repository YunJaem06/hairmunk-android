package com.hairmunk.app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hairmunk.app.model.CartItem

@Dao
interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("SELECT * FROM cart_item")
    suspend fun load(): List<CartItem>
}