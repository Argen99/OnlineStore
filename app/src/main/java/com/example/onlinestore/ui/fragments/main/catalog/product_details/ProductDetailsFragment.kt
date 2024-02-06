package com.example.onlinestore.ui.fragments.main.catalog.product_details

import android.annotation.SuppressLint
import android.view.View
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.core.extensions.isEllipsized
import com.example.onlinestore.databinding.FragmentProductDetailsBinding
import com.example.onlinestore.ui.adapter.ImagePagerAdapter
import com.example.onlinestore.ui.adapter.ProductInfoAdapter
import com.example.onlinestore.ui.fragments.main.catalog.CatalogViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, CatalogViewModel>(R.layout.fragment_product_details) {

    override val binding by viewBinding(FragmentProductDetailsBinding::bind)
    override val viewModel by activityViewModel<CatalogViewModel>()

    private val infoAdapter: ProductInfoAdapter by lazy {
        ProductInfoAdapter()
    }

    private val imagePagerAdapter: ImagePagerAdapter by lazy {
        ImagePagerAdapter()
    }

    override fun initialize() {
        binding.rvProductInfo.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = infoAdapter
        }

        binding.vpProduct.apply {
            adapter = imagePagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        binding.springDotsIndicator.attachTo(binding.vpProduct)
    }

    override fun setupListeners() = with(binding) {
        tvIngredientsMore.setOnClickListener {
            if (tvIngredientsValue.maxLines == 2) {
                tvIngredientsValue.maxLines = Int.MAX_VALUE
                tvIngredientsMore.text = getString(R.string.hide)
            } else {
                tvIngredientsValue.maxLines = 2
                tvIngredientsMore.text = getString(R.string.more)
            }
        }

        tvDescriptionHide.setOnClickListener {
            if (productDescContainer.visibility == View.VISIBLE) {
                productDescContainer.isVisible = false
                tvDescriptionHide.text = getString(R.string.more)
            } else {
                productDescContainer.isVisible = true
                tvDescriptionHide.text = getString(R.string.hide)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentProduct.collectLatest { data ->
                data?.let { product ->
                    binding.ivFavorite.setImageResource(
                        if (product.isFavorite) R.drawable.ic_heart_active
                        else R.drawable.ic_heart_default
                    )
                    binding.tvProductTitle.text = product.title
                    binding.tvProductSubtitle.text = "Доступно для заказа ${product.available} штук"
                    if (product.feedback != null) {
                        binding.ratingBar.rating = product.feedback!!.rating.toFloat()
                        binding.tvRating.text =
                            "${product.feedback!!.rating} · ${product.feedback!!.count} отзыва"
                    } else {
                        binding.ratingBar.rating = 0f
                    }
                    binding.tvPrice.text = product.price.priceWithDiscount
                    binding.tvOldPrice.text = product.price.price
                    binding.tvDiscount.text = "${product.price.discount}%"
                    binding.btnGoToBrand.text = product.title
                    binding.tvDescription.text = product.description

                    infoAdapter.submitList(product.info)
                    binding.tvIngredientsValue.text = product.ingredients
                    imagePagerAdapter.submitList(product.images)

                    binding.tvIngredientsValue.doOnLayout {
                        binding.tvIngredientsMore.isVisible = binding.tvIngredientsValue.isEllipsized()
                    }
                    binding.btnPrice.text = product.price.priceWithDiscount
                    binding.btnOldPrice.text = product.price.price
                }
            }
        }
    }
}
