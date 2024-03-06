package com.ashoka.capstonedicoding.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ashoka.capstonedicoding.BuildConfig
import com.ashoka.capstonedicoding.R
import com.ashoka.capstonedicoding.databinding.FragmentDetailMoviesBinding
import com.ashoka.core.data.resource.Resource
import com.ashoka.core.domain.model.DetailMovie
import com.ashoka.core.domain.model.Movie
import com.ashoka.core.utils.DataMapper
import com.ashoka.core.utils.EndPointMovie
import com.ashoka.core.utils.EndPointMovie.ID_MOVIE
import com.ashoka.core.utils.EndPointMovie.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailMoviesFragment : Fragment(R.layout.fragment_detail_movies) {

    private val binding by viewBinding(FragmentDetailMoviesBinding::bind)
    private val args : DetailMoviesFragmentArgs by navArgs()
    private val detailMoviesViewModel : DetailMoviesViewModel by viewModels()
    private var isFavorite by Delegates.notNull<Boolean>()
    private var movie:Movie? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDetailMovie()

        val getIdMovie = arguments?.getInt(ID_MOVIE)
        getIdMovie?.let { setFavMovie(it) }
        setFavMovie(args.id)

        setFavorite()
    }

    private fun setFavMovie(idMovie : Int) {
        detailMoviesViewModel.getFavMovieById(idMovie).observe(viewLifecycleOwner){
            Log.d("setFavMovie", "ID=$idMovie, $it")
            isFavorite = it
            setButtonFavorite(it)
        }
    }

    private fun setButtonFavorite(flag : Boolean) = with(binding){
        Log.d("setButtonFavorite", "flag = $flag")
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
                    Log.d("setFavorite", "IF movie = $movie")
                    detailMoviesViewModel.insertFavMovie(movie = movie)
                }
                setButtonFavorite(false)
                false
            } else{
                movie?.let {movie ->
                    Log.d("setFavorite", "ELSE isFavorite = $isFavorite")
                    detailMoviesViewModel.deleteFavMovie(movie = movie)
                    setButtonFavorite(true)
                }
                true
            }
        }
    }

    private fun observeDetailMovie() = with(binding){
        val movieId = args.id
        detailMoviesViewModel.getDetailMovie(
                token = "Bearer ${BuildConfig.API_KEY}", movieId = movieId, language = "en-US")

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                detailMoviesViewModel.detailMovie.collectLatest {
                    it?.let { response ->
                        when(response){
                            is Resource.Error -> {
                            }
                            is Resource.Loading -> {

                            }
                            is Resource.Success -> {

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
}