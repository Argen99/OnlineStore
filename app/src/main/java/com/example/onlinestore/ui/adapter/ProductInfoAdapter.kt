package com.example.onlinestore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.data.model.InfoDto
import com.example.domain.model.InfoModel
import com.example.onlinestore.databinding.ItemProductInfoBinding
import com.example.onlinestore.ui.model.InfoUI

class ProductInfoAdapter: ListAdapter<InfoUI, ProductInfoAdapter.ProductInfoViewPager>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductInfoViewPager(
        ItemProductInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: ProductInfoViewPager, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ProductInfoViewPager(private val binding: ItemProductInfoBinding): ViewHolder(binding.root) {
        fun onBind(model: InfoUI) = with(binding) {
            tvName.text = model.title
            tvValue.text = model.value
        }
    }
    companion object {
        val callback = object : DiffUtil.ItemCallback<InfoUI>() {
            override fun areItemsTheSame(oldItem: InfoUI, newItem: InfoUI) =
                oldItem.hashCode() == newItem.hashCode()

            override fun areContentsTheSame(oldItem: InfoUI, newItem: InfoUI) =
                oldItem == newItem
        }
    }
}