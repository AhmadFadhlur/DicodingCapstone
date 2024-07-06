package com.ashoka.capstonedicoding.utils

import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun behaviorSystemUI(fragment: Fragment, isHide: Boolean = false) {
    val activity = fragment.requireActivity()
    WindowCompat.setDecorFitsSystemWindows(activity.window, !isHide)
    val controller = WindowInsetsControllerCompat(activity.window, fragment.requireView())
    val typesBar = WindowInsetsCompat.Type.systemBars()
    val typesNav = WindowInsetsCompat.Type.navigationBars()

    if (isHide) {
        controller.let {
            it.hide(typesBar)
            it.hide(typesNav)
            it.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        controller.show(typesBar)
        controller.show(typesNav)
    }
}

fun onNavDestinationSelected(
    itemId: Int,
    navController: NavController
): Boolean {
    val builder = NavOptions.Builder()
        .setLaunchSingleTop(true)
    if (navController.currentDestination!!.parent!!.findNode(itemId) is ActivityNavigator.Destination) {
        builder.setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
            .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
            .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
    } else {
        builder.setEnterAnim(androidx.navigation.ui.R.animator.nav_default_enter_anim)
            .setExitAnim(androidx.navigation.ui.R.animator.nav_default_exit_anim)
            .setPopEnterAnim(androidx.navigation.ui.R.animator.nav_default_pop_enter_anim)
            .setPopExitAnim(androidx.navigation.ui.R.animator.nav_default_pop_exit_anim)
    }
    //if (itemId == getChildAt(0).id) {
    //builder.setPopUpTo(findStartDestination(navController.graph)!!.id, true)
    // }
    builder.setPopUpTo(itemId, true)
    val options = builder.build()
    return try {
        navController.navigate(itemId, null, options)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}

