package com.example.onlinestore.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.onlinestore.R
import com.example.onlinestore.databinding.ItemProfileCardBinding
import com.example.onlinestore.ui.model.ProfileCard

class ProfileCardAdapter(
    val context: Context,
    val onFavoriteClick: () -> Unit
) : ListAdapter<ProfileCard, ProfileCardAdapter.ProfileCardViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileCardViewHolder(
        ItemProfileCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProfileCardViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ProfileCardViewHolder(val binding: ItemProfileCardBinding) :
        ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(model: ProfileCard) {
            binding.ivIcon.setImageResource(model.icon)
            binding.tvTitle.text = model.title
            if (model.value != null && model.value != 0) {
                binding.tvValue.isVisible = true
                val text = when (model.value) {
                    1 -> "товар"
                    in 2..4 -> "товара"
                    else -> "товаров"
                }
                binding.tvValue.text = "${model.value} $text"
            } else {
                binding.tvValue.isVisible = false
            }
        }

        init {
            binding.root.setOnClickListener {
                if (getItem(absoluteAdapterPosition).title == context.getString(R.string.favorite))
                    onFavoriteClick()
            }
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<ProfileCard>() {
            override fun areItemsTheSame(oldItem: ProfileCard, newItem: ProfileCard) =
                oldItem.hashCode() == newItem.hashCode()

            override fun areContentsTheSame(oldItem: ProfileCard, newItem: ProfileCard) =
                oldItem == newItem
        }
    }
}