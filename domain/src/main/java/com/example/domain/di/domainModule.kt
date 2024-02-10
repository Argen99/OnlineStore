package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.example.domain.use_case.GetProductsUseCase
import com.example.domain.use_case.AddProductToFavoritesUseCase
import com.example.domain.use_case.GetFavoriteProductsUseCase
import com.example.domain.use_case.RemoveProductFromFavoritesUseCase
import com.example.domain.use_case.GetUserDataUseCase
import com.example.domain.use_case.SaveUserDataUseCase
import com.example.domain.use_case.GetFavoritesCountUseCase
import com.example.domain.use_case.ClearUserDataUseCase
import com.example.domain.use_case.ClearFavoriteProductsUseCase

val domainModule = module {

    factoryOf(::GetProductsUseCase)
    factoryOf(::AddProductToFavoritesUseCase)
    factoryOf(::GetFavoriteProductsUseCase)
    factoryOf(::RemoveProductFromFavoritesUseCase)
    factoryOf(::GetUserDataUseCase)
    factoryOf(::SaveUserDataUseCase)
    factoryOf(::GetFavoritesCountUseCase)
    factoryOf(::ClearUserDataUseCase)
    factoryOf(::ClearFavoriteProductsUseCase)
}