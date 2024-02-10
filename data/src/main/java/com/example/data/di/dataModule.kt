package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.room.ProductDataBase
import com.example.data.local.room.ProductsDao
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.data.local.prefs.UserPreferences
import com.example.data.local.prefs.UserRepositoryImpl
import com.example.data.remote.ProductRepositoryImpl
import com.example.data.remote.ProductApiService
import com.example.domain.repository.ProductRepository
import com.example.domain.repository.UserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    singleOf(::ProductRepositoryImpl) {
        bind<ProductRepository>()
    }
    singleOf(::UserPreferences)
    singleOf(::UserRepositoryImpl) {
        bind<UserRepository>()
    }
    factoryOf(::provideOkHttpClient)
    factoryOf(::provideForecastApi)
    factoryOf(::provideRetrofit)
    singleOf(::providesRoomDatabase)
    singleOf(::providesProductDao)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://run.mocky.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun provideForecastApi(retrofit: Retrofit): ProductApiService {
    return retrofit.create(ProductApiService::class.java)
}

fun providesRoomDatabase(context: Context) =
    Room.databaseBuilder(context, ProductDataBase::class.java, "online_store.db")
        .build()

fun providesProductDao(productDataBase: ProductDataBase): ProductsDao =
    productDataBase.getProductsDao()