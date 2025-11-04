package com.pasteleria1000sabores.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pasteleria1000sabores.data.model.CartItem
import com.pasteleria1000sabores.data.model.CartItemWithProduct

@Dao
interface CartDao {
    @Transaction
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): LiveData<List<CartItemWithProduct>>

    @Query("SELECT * FROM cart_items WHERE productId = :productId")
    suspend fun getCartItemByProductId(productId: Int): CartItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Update
    suspend fun update(cartItem: CartItem)

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Query("SELECT COUNT(*) FROM cart_items")
    fun getCartItemCount(): LiveData<Int>
}
