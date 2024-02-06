package com.example.onlinestore.ui.fragments.main.promotions

import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.databinding.FragmentPromotionsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PromotionsFragment :
    BaseFragment<FragmentPromotionsBinding, PromotionsViewModel>(R.layout.fragment_promotions) {

    override val binding by viewBinding(FragmentPromotionsBinding::bind)
    override val viewModel by viewModel<PromotionsViewModel>()
}