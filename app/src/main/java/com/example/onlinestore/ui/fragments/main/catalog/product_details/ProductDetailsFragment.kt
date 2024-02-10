package com.example.onlinestore.ui.fragments.main.catalog.product_details

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.core.extensions.getNoun
import com.example.onlinestore.core.extensions.isEllipsized
import com.example.onlinestore.databinding.FragmentProductDetailsBinding
import com.example.onlinestore.ui.adapter.ImagePagerAdapter
import com.example.onlinestore.ui.adapter.ProductInfoAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductDetailsViewModel>(R.layout.fragment_product_details) {

    override val binding by viewBinding(FragmentProductDetailsBinding::bind)
    override val viewModel by viewModel<ProductDetailsViewModel>()

    private val infoAdapter: ProductInfoAdapter by lazy {
        ProductInfoAdapter()
    }
    private val imagePagerAdapter: ImagePagerAdapter by lazy {
        ImagePagerAdapter()
    }
    private val args by navArgs<ProductDetailsFragmentArgs>()

    override fun initialize() {
        constructViews()
        setUIData()
    }

    @SuppressLint("SetTextI18n")
    private fun setUIData() = with(args.product) {
        binding.ivFavorite.setImageResource(
            if (isFavorite) R.drawable.ic_heart_active
            else R.drawable.ic_heart_default
        )
        binding.tvProductTitle.text = title
        binding.tvProductSubtitle.text = subtitle
        binding.tvProductCount.text =
            "Доступно для заказа $available ${available.getNoun("штука", "штуки", "штук")}"
        if (feedback != null) {
            binding.ratingBar.rating = feedback.rating.toFloat()
            binding.tvRating.text =
                "${feedback.rating} · ${feedback.count}" +
                        " ${feedback.count.getNoun("отзыв", "отзыва", "отзывов")}"
        } else {
            binding.ratingBar.rating = 0f
        }
        binding.tvPrice.text = "${price.priceWithDiscount}${price.unit}"
        binding.tvOldPrice.text = "${price.price}${price.unit}"
        binding.tvDiscount.text = "${price.discount}%"
        binding.btnGoToBrand.text = title
        binding.tvDescription.text = description

        infoAdapter.submitList(info)
        binding.tvIngredientsValue.text = ingredients
        imagePagerAdapter.submitList(images)

        binding.tvIngredientsValue.doOnLayout {
            binding.tvIngredientsMore.isVisible =
                binding.tvIngredientsValue.isEllipsized()
        }
        binding.btnPrice.text = price.priceWithDiscount
        binding.btnOldPrice.text = price.price
    }

    private fun constructViews() {
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

        binding.ivFavorite.setOnClickListener {
            if (args.product.isFavorite) {
                viewModel.removeProductFromFavorites(args.product)
                binding.ivFavorite.setImageResource(R.drawable.ic_heart_default)
            } else {
                viewModel.addProductToFavorites(args.product)
                binding.ivFavorite.setImageResource(R.drawable.ic_heart_active)
            }
            args.product.isFavorite = !args.product.isFavorite
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}