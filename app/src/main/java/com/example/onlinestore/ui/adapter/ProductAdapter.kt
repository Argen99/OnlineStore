package com.example.onlinestore.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.model.ProductModel
import com.example.onlinestore.R
import com.example.onlinestore.core.utils.Object.IMAGES_MAP
import com.example.onlinestore.databinding.ItemProductBinding

class ProductAdapter(
    val onFavoriteClick: (product: ProductModel) -> Unit,
    val onItemClick: (id: String) -> Unit,
) : ListAdapter<ProductModel, ProductAdapter.ProductViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        ViewHolder(binding.root) {

        private val imagePagerAdapter: ImagePagerAdapter by lazy {
            ImagePagerAdapter()
        }

        @SuppressLint("SetTextI18n")
        fun onBind(product: ProductModel) = with(binding) {
            tvOldPrice.text = product.price.price
            tvProductPrice.text = "${product.price.priceWithDiscount} ${product.price.unit}"
            tvProductTitle.text = product.title
            tvProductSubtitle.text = product.subtitle
            tvPriceDiscount.text = "${product.price.discount}%"
            binding.btnFavorite.setImageResource(
                if (product.isFavorite) R.drawable.ic_heart_active else R.drawable.ic_heart_default
            )
            if (product.feedback != null) {
                ratingContainer.isVisible = true
                tvRating.text = product.feedback!!.rating.toString()
                tvReviewsCount.text = "(${product.feedback!!.count})"
            }

            vpImage.apply {
                adapter = imagePagerAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
            IMAGES_MAP.forEach {
                if ((product.id == it.key)) imagePagerAdapter.submitList(it.value)
            }
            springDotsIndicator.attachTo(vpImage)
        }

        init {
            binding.btnFavorite.setOnClickListener {
                val product = getItem(absoluteAdapterPosition)
                product.isFavorite = !product.isFavorite
                binding.btnFavorite.setImageResource(
                    if (product.isFavorite) R.drawable.ic_heart_active else R.drawable.ic_heart_default
                )
                onFavoriteClick(product)
            }

            binding.root.setOnClickListener {
                onItemClick(getItem(absoluteAdapterPosition).id)
            }
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel) =
                oldItem == newItem
        }
    }
}