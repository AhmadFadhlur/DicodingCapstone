package com.ashoka.capstonedicoding.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.databinding.FragmentDetailMoviesBinding
import com.ashoka.capstonedicoding.databinding.FragmentSettingBinding
import com.ashoka.core.adapter.FavoriteMovieAdapter
import com.ashoka.core.domain.model.Movie
import com.ashoka.core.utils.EndPointMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_setting) {

    private val binding by viewBinding(FragmentSettingBinding::bind)
    private val settingViewModel : SettingViewModel by viewModels()
    private lateinit var  favMovieAdapter : FavoriteMovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavMovie()
        setFavMovieAdapter()
    }

    private fun observeFavMovie(){
        settingViewModel.favMovie.observe(viewLifecycleOwner){movie ->
            favMovieAdapter.submitData(movie as ArrayList<Movie>)
            Log.i("HASIL DB", movie.toString())
        }
    }

    private fun setFavMovieAdapter() = with(binding.rvFavMovie) {
        favMovieAdapter = FavoriteMovieAdapter{
            toDetailFrag(it)
        }
        layoutManager = LinearLayoutManager(requireContext())
        adapter = favMovieAdapter

    }

    private fun toDetailFrag(movie: Movie){
        val direction = SettingFragmentDirections.actionSettingNavigationToDetailMoviesFragment().apply {
            this.id = movie.id
        }
        val idBundle = Bundle().apply {
            putInt(EndPointMovie.ID_MOVIE, movie.id)
        }
        findNavController().navigate(direction)
    }
}