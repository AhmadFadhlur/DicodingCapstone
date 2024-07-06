package com.ashoka.capstonedicoding.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.databinding.ActivityMainBinding
import com.ashoka.capstonedicoding.utils.False
import com.ashoka.capstonedicoding.utils.True
import com.ashoka.capstonedicoding.utils.onNavDestinationSelected
import com.ashoka.capstonedicoding.utils.setVisibleOrGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment
        navController = navHostFragment.navController

        binding.bubbleTabBar.addBubbleListener{ id -> onNavDestinationSelected(id,navController) }

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.detailMoviesFragment -> binding.bubbleTabBar.setVisibleOrGone(Boolean.False)

                else -> binding.bubbleTabBar.setVisibleOrGone(Boolean.True)
            }
            binding.bubbleTabBar.setSelectedWithId(destination.id, false)
        }
    }

    private fun navigationChange(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navHostContainer, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}