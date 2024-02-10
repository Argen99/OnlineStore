package com.example.onlinestore.core.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.onlinestore.R

fun Fragment.activityNavController() =
    requireActivity().findNavController(R.id.nav_host_fragment_activity_main)

fun NavController.navigateSafely(@IdRes actionId: Int, bundle: Bundle? = null) {
    currentDestination?.getAction(actionId)?.let { navigate(actionId, bundle) }
}