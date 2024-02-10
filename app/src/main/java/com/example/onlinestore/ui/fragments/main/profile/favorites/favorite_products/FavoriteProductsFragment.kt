package com.example.onlinestore.ui.fragments.main.profile.favorites.favorite_products

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.model.ProductModel
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.databinding.FragmentFavoriteProductsBinding
import com.example.onlinestore.ui.adapter.ProductAdapter
import com.example.onlinestore.ui.model.ProductUI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteProductsFragment(private val position: Int) :
    BaseFragment<FragmentFavoriteProductsBinding, FavoriteProductsViewModel>(R.layout.fragment_favorite_products) {

    override val binding by viewBinding(FragmentFavoriteProductsBinding::bind)
    override val viewModel by viewModel<FavoriteProductsViewModel>()

    private val favoritesAdapter: ProductAdapter by lazy {
        ProductAdapter(onFavoriteClick = ::onFavoriteClick, isFavorites = true)
    }

    override fun initialize() {
        binding.rvFavorites.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = favoritesAdapter
        }
    }

    override fun launchObservers() {
        if (position == 0) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.favoritesState.collectLatest {
                    favoritesAdapter.submitList(it)
                }
            }
        }
    }

    private fun onFavoriteClick(product: ProductUI) {
        viewModel.removeProductFromFavorites(product)
    }
}