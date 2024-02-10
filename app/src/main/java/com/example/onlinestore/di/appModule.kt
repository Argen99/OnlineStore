package com.example.onlinestore.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.onlinestore.ui.fragments.main.home.HomeViewModel
import com.example.onlinestore.ui.fragments.auth.sign_up.SignUpViewModel
import com.example.onlinestore.ui.fragments.main.catalog.CatalogViewModel
import com.example.onlinestore.ui.fragments.main.catalog.product_details.ProductDetailsViewModel
import com.example.onlinestore.ui.fragments.main.profile.ProfileViewModel
import com.example.onlinestore.ui.fragments.main.profile.favorites.FavoritesViewModel
import com.example.onlinestore.ui.fragments.main.profile.favorites.favorite_products.FavoriteProductsViewModel

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::CatalogViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::FavoritesViewModel)
    viewModelOf(::FavoriteProductsViewModel)
    viewModelOf(::ProductDetailsViewModel)
}