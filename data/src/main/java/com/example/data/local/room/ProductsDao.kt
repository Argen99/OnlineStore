package com.example.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.ProductDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductDto>>

    @Query("SELECT COUNT(id) FROM products")
    fun getCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: ProductDto)

    @Delete
    suspend fun deleteProduct(product: ProductDto)

    @Query("DELETE FROM products")
    suspend fun deleteAll()
}