package com.example.onlinestore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.onlinestore.databinding.ItemImageBinding

class ImagePagerAdapter(
    val onItemClick: (() -> Unit)? = null
) :
    ListAdapter<Int, ImagePagerAdapter.ImagePagerViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImagePagerViewHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ImagePagerViewHolder(val binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(image: Int) {
            binding.productImage.setImageResource(image)
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke()
            }
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int) =
                oldItem.hashCode() == newItem.hashCode()

            override fun areContentsTheSame(oldItem: Int, newItem: Int) =
                oldItem == newItem
        }
    }
}