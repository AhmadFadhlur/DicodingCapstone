package com.ashoka.capstonedicoding.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.databinding.FragmentDetailMoviesBinding


class DetailMoviesFragment : Fragment(R.layout.fragment_detail_movies) {

    private val binding by viewBinding(FragmentDetailMoviesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}