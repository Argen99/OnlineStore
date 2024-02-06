package com.example.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.example.domain.use_case.GetProductsUseCase
import com.example.domain.use_case.AddToFavoritesUseCase
import com.example.domain.use_case.GetFavoritesUseCase
import com.example.domain.use_case.RemoveFromFavoritesUseCase
import com.example.domain.use_case.GetUserDataUseCase
import com.example.domain.use_case.SaveUserDataUseCase

val domainModule = module {

    factoryOf(::GetProductsUseCase)
    factoryOf(::AddToFavoritesUseCase)
    factoryOf(::GetFavoritesUseCase)
    factoryOf(::RemoveFromFavoritesUseCase)
    factoryOf(::GetUserDataUseCase)
    factoryOf(::SaveUserDataUseCase)
}