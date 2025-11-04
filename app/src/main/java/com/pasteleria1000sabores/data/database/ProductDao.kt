package com.pasteleria1000sabores.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pasteleria1000sabores.data.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE available = 1")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId: Int): Product?

    @Query("SELECT * FROM products WHERE categoryId = :categoryId AND available = 1")
    fun getProductsByCategory(categoryId: Int): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE (name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%') AND available = 1")
    fun searchProducts(query: String): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)
}
