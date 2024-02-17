package com.ashoka.capstonedicoding.ui.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.databinding.FragmentHomeMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMoviesFragment : Fragment(R.layout.fragment_home_movies) {

    private val binding by viewBinding(FragmentHomeMoviesBinding::bind)
    private val homeMoviesViewModel : HomeMoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}