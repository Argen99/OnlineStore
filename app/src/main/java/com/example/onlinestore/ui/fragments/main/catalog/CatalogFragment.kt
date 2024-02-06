package com.example.onlinestore.ui.fragments.main.catalog

import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.model.ProductModel
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.core.utils.Object.CATEGORIES
import com.example.onlinestore.databinding.FragmentCatalogBinding
import com.example.onlinestore.ui.adapter.ProductAdapter
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CatalogFragment :
    BaseFragment<FragmentCatalogBinding, CatalogViewModel>(R.layout.fragment_catalog) {

    private val productsAdapter by lazy {
        ProductAdapter(::onFavoriteClick, ::onItemClick)
    }

    override val binding by viewBinding(FragmentCatalogBinding::bind)
    override val viewModel by activityViewModel<CatalogViewModel>()

    override fun initialize() {

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productsAdapter
        }

        CATEGORIES.forEach { category ->
            val radioButton =
                layoutInflater.inflate(R.layout.item_rb_category, null) as RadioButton
            radioButton.id = View.generateViewId()
            val params = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                marginStart = resources.getDimensionPixelSize(R.dimen.radio_btn_margin_between_items)
            }
            radioButton.layoutParams = params
            radioButton.text = category.name
            binding.rgCategories.addView(radioButton)
        }
    }

    override fun setupListeners() {
        binding.rgCategories.setOnCheckedChangeListener { _, i ->
            binding.root.findViewById<RadioButton>(i)?.let { radioButton ->
                val text = radioButton.text.toString()
                viewModel.filterByCategory(text)
            }
        }

        binding.spinnerView.setOnSpinnerItemSelectedListener(OnSpinnerItemSelectedListener<String?>
        { _, _, _, newItem ->
            newItem?.let {
                viewModel.sortProducts(it)
            }
        })
    }

    override fun launchObservers() {
        viewModel.productsState.spectateUiState(
            loading = {
                binding.progressCircular.isVisible = true
            },
            success = {
                binding.progressCircular.isVisible = false
                productsAdapter.submitList(it)
            },
            error = {
                binding.progressCircular.isVisible = false
            }
        )
    }

    private fun onFavoriteClick(product: ProductModel) {
        viewModel.onFavoriteClick(product)
    }

    private fun onItemClick(id: String) {
        viewModel.onItemClick(id)
        findNavController().navigate(R.id.action_catalogFragment_to_productDetailsFragment)
    }
}