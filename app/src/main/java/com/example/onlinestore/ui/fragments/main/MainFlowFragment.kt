package com.example.onlinestore.ui.fragments.main

import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFlowFragment
import com.example.onlinestore.databinding.FragmentMainFlowBinding

class MainFlowFragment : BaseFlowFragment(R.layout.fragment_main_flow, R.id.nav_host_main_flow) {
    private val binding by viewBinding(FragmentMainFlowBinding::bind)

    override fun setupNavigation(navController: NavController) {

        val navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navGraph.setStartDestination(R.id.catalogFragment)
        navController.graph = navGraph

        binding.bottomNavView.setupWithNavController(navController)
    }
}