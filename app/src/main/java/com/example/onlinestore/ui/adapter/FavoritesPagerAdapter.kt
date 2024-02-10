package com.example.onlinestore.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.onlinestore.ui.fragments.main.profile.favorites.favorite_products.FavoriteProductsFragment

class FavoritesPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return FavoriteProductsFragment(position)
    }
}