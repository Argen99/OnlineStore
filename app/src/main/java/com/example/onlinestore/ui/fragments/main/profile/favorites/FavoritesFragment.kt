package com.example.onlinestore.ui.fragments.main.profile.favorites

import android.view.LayoutInflater
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.databinding.FragmentFavoritesBinding
import com.example.onlinestore.ui.adapter.FavoritesPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(R.layout.fragment_favorites) {

    override val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val viewModel by viewModel<FavoritesViewModel>()

    private val pagerAdapter: FavoritesPagerAdapter by lazy {
        FavoritesPagerAdapter(this)
    }
    private val tabTitles = listOf("Товары", "Бренды")

    override fun initialize() {
        binding.vpFavorites.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.vpFavorites) { tab, position ->
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null)
                    as TextView
            tab.text = tabTitles[position]
            tab.customView = textView
        }.attach()
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}