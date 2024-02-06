package com.example.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.room.converters.FeedbackTypeConverter
import com.example.data.local.room.converters.InfoTypeListConverter
import com.example.data.local.room.converters.PriceTypeConverter
import com.example.data.local.room.converters.StringListConverter
import com.example.data.model.ProductDto

@Database(
    version = 1,
    entities = [ProductDto::class]
)
@TypeConverters(
    PriceTypeConverter::class, FeedbackTypeConverter::class, InfoTypeListConverter::class,
    StringListConverter::class
)
abstract class ProductDataBase: RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao
}