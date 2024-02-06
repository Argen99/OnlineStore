package com.example.onlinestore.ui.fragments.main.profile

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.databinding.FragmentProfileBinding
import com.example.onlinestore.ui.adapter.ProfileCardAdapter
import com.example.onlinestore.ui.model.ProfileCard
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override val binding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel by viewModel<ProfileViewModel>()

    private val cardAdapter: ProfileCardAdapter by lazy {
        ProfileCardAdapter()
    }
    private var cards: List<ProfileCard> = emptyList()

    override fun initialize() {
        cards = listOf(
            ProfileCard(title = "Избранное", icon = R.drawable.ic_heart_default),
            ProfileCard(title = "Магазины", icon = R.drawable.ic_store),
            ProfileCard(title = "Обратная связь", icon = R.drawable.ic_message),
            ProfileCard(title = "Оферта", icon = R.drawable.ic_document),
            ProfileCard(title = "Возврат товара", icon = R.drawable.ic_return),
        )

        binding.rvProfile.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = cardAdapter
        }
        cardAdapter.submitList(cards)
    }

    @SuppressLint("SetTextI18n")
    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userState.collectLatest { user ->
                binding.tvUserName.text = "${user?.name} ${user?.surname}"
                binding.tvUserPhone.text = user?.phone
            }
        }
    }
}