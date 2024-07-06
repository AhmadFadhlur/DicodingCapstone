package com.ashoka.favorite.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.di.FavoriteModuleDependencies
import com.ashoka.core.adapter.FavoriteMovieAdapter
import com.ashoka.core.domain.model.Movie
import com.ashoka.favorite.R
import com.ashoka.favorite.databinding.FragmentFavoriteBinding
import com.ashoka.favorite.di.DaggerFavoriteComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val favoriteViewModel : FavoriteViewModel by viewModels{
        factory
    }
    private lateinit var  favMovieAdapter : FavoriteMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFavMovieAdapter()
        observeFavMovie()

    }

    private fun observeFavMovie(){
        favoriteViewModel.favMovie.observe(viewLifecycleOwner){movie ->
            favMovieAdapter.submitData(movie as ArrayList<Movie>)

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
        favoriteViewModel.deleteMovie(movie)

    }
}