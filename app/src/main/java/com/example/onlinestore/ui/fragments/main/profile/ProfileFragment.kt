package com.example.onlinestore.ui.fragments.main.profile

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.core.extensions.activityNavController
import com.example.onlinestore.core.extensions.navigateSafely
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
        ProfileCardAdapter(requireContext(), ::onFavoriteClick)
    }
    private var cards: MutableList<ProfileCard> = mutableListOf()

    override fun initialize() {
        cards = mutableListOf(
            ProfileCard(title = getString(R.string.favorite), icon = R.drawable.ic_heart_default),
            ProfileCard(title = getString(R.string.stores), icon = R.drawable.ic_store),
            ProfileCard(title = getString(R.string.feedback), icon = R.drawable.ic_message),
            ProfileCard(title = getString(R.string.offer), icon = R.drawable.ic_document),
            ProfileCard(title = getString(R.string.return_of_goods), icon = R.drawable.ic_return),
        )

        binding.rvProfile.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = cardAdapter
        }
    }

    override fun setupListeners() {
        binding.btnExit.setOnClickListener {
            viewModel.exit()
            activityNavController().navigateSafely(R.id.action_global_authFlowFragment)
        }
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userState.collectLatest { user ->
                binding.tvUserName.text = "${user?.name} ${user?.surname}"
                binding.tvUserPhone.text = user?.phone
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoritesCount.collectLatest {
                cards[0] = cards[0].copy(value = it)
                cardAdapter.submitList(cards.toList())
            }
        }
    }

    private fun onFavoriteClick() {
        findNavController().navigate(R.id.action_profileFragment_to_favoritesFragment)
    }
}