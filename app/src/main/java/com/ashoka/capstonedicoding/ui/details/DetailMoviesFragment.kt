package com.ashoka.capstonedicoding.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.BuildConfig
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.databinding.FragmentDetailMoviesBinding
import com.ashoka.capstonedicoding.utils.False
import com.ashoka.capstonedicoding.utils.True
import com.ashoka.capstonedicoding.utils.behaviorSystemUI
import com.ashoka.capstonedicoding.utils.setVisibleOrGone
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.Movie
import com.ashoka.core.utils.DataMapper
import com.ashoka.core.utils.EndPointMovie.ID_MOVIE
import com.ashoka.core.utils.EndPointMovie.IMAGE_BASE_URL
import com.ashoka.core.utils.EndPointMovie.TO_DETAIL
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailMoviesFragment : Fragment(R.layout.fragment_detail_movies) {

    private val binding by viewBinding(FragmentDetailMoviesBinding::bind)
    private val detailMoviesViewModel : DetailMoviesViewModel by viewModels()
    private var isFavorite by Delegates.notNull<Boolean>()
    private var movie:Movie? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFavMovie()
        setFavorite()
        onBackPressed()
    }

    private fun setFavMovie() {
        val idMovie = arguments?.getInt(ID_MOVIE)
        val flagDetail = arguments?.getString(TO_DETAIL)
        if (idMovie != null && flagDetail != null ){
            detailMoviesViewModel.getFavMovieById(idMovie).observe(viewLifecycleOwner){
                isFavorite = it
                setButtonFavorite(it)
            }
            when(flagDetail) {
                "movie" ->{ observeDetailMovie(idMovie)}
                "tvshows" ->{ observeDetailTvShows(idMovie)}
                else -> { Log.e("setFavMovie", "error")}
            }
        }
    }

    private fun setButtonFavorite(flag : Boolean) = with(binding){
        if (flag){
            btnCollection.setIconResource(R.drawable.ic_fav_full)
            btnCollection.text = getString(R.string.added_to_collection)
        } else {
            btnCollection.setIconResource(R.drawable.ic_fav_border)
            btnCollection.text = getString(R.string.add_to_collection)
        }
    }


    private fun setFavorite() = with(binding){
        btnCollection.setOnClickListener {
            isFavorite = if (!isFavorite){
                movie?.let {movie ->
                    detailMoviesViewModel.insertFavMovie(movie = movie)
                }
                setButtonFavorite(false)
                false
            } else{
                movie?.let {movie ->
                    detailMoviesViewModel.deleteFavMovie(movie = movie)
                    setButtonFavorite(true)
                }
                true
            }
        }
    }

    private fun observeDetailTvShows(idMovie: Int) = with(binding){
        detailMoviesViewModel.getDetailTvshows(
            token = "Bearer ${BuildConfig.API_KEY}", movieId = idMovie, language = "en-US")

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                detailMoviesViewModel.detailTvshows.collectLatest {
                    it?.let { response ->
                        when(response){
                            is Resource.Error -> {
                                progressBar.setVisibleOrGone(Boolean.False)
                            }
                            is Resource.Loading -> {
                                progressBar.setVisibleOrGone(Boolean.True)
                            }
                            is Resource.Success -> {
                                progressBar.setVisibleOrGone(Boolean.False)

                                response.data?.let {detailMovie ->
                                    movie = DataMapper.mapDetailDomaintoMovieDomain(detailMovie)
                                    Glide.with(requireContext()).load("$IMAGE_BASE_URL${detailMovie.posterPath}").into(subPoster)
                                    Glide.with(requireContext()).load("$IMAGE_BASE_URL${detailMovie.backdropPath}").into(posterTopBar)
                                    tvOverview.text = detailMovie.overview
                                    tvTitleDetail.text = detailMovie.title
                                    tvReleaseDate.text = detailMovie.releaseDate
                                    tvOgTittle.text = detailMovie.originalTitle

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeDetailMovie(idMovie: Int) = with(binding){
        detailMoviesViewModel.getDetailMovie(
                token = "Bearer ${BuildConfig.API_KEY}", movieId = idMovie, language = "en-US")

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                detailMoviesViewModel.detailMovie.collectLatest {
                    it?.let { response ->
                        when(response){
                            is Resource.Error -> {progressBar.setVisibleOrGone(Boolean.False)}
                            is Resource.Loading -> {progressBar.setVisibleOrGone(Boolean.True)}
                            is Resource.Success -> {
                                progressBar.setVisibleOrGone(Boolean.False)
                                response.data?.let {detailMovie ->
                                    movie = DataMapper.mapDetailDomaintoMovieDomain(detailMovie)
                                    Glide.with(requireContext()).load("$IMAGE_BASE_URL${detailMovie.posterPath}").into(subPoster)
                                    Glide.with(requireContext()).load("$IMAGE_BASE_URL${detailMovie.backdropPath}").into(posterTopBar)
                                    tvOverview.text = detailMovie.overview
                                    tvTitleDetail.text = detailMovie.title
                                    tvReleaseDate.text = detailMovie.releaseDate
                                    tvOgTittle.text = detailMovie.originalTitle

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onBackPressed() = with(binding){
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }



    override fun onResume() {
       behaviorSystemUI(this, true)
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }


}