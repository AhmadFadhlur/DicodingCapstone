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
import com.ashoka.core.utils.EndPointMovie
import com.ashoka.core.utils.EndPointMovie.IMAGE_BASE_URL
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMoviesFragment : Fragment(R.layout.fragment_detail_movies) {

    private val binding by viewBinding(FragmentDetailMoviesBinding::bind)
    private val args : DetailMoviesFragmentArgs by navArgs()
    private val detailMoviesViewModel : DetailMoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDetailMovie()

    }

    private fun observeDetailMovie() = with(binding){
        val movieId = args.id

        detailMoviesViewModel.getDetailMovie(
                token = "Bearer ${BuildConfig.API_KEY}", movieId = movieId, language = "en-US")

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                detailMoviesViewModel.detailMovie.collectLatest {
                    Log.d("collectLatest", "judul = ${it?.data?.title}" )
                    it?.let { response ->
                        when(response){
                            is Resource.Error -> {
                                Log.d("observeDetailMovie", "MASUK ERROR" )
                            }
                            is Resource.Loading -> {

                            }
                            is Resource.Success -> {
                                Log.d("observeDetailMovie", "MASUK Success" )
                                response.data?.let {
                                    Log.d("detailMovie", "judul = ${it.title.toString()}" )
                                    Glide.with(requireContext()).load("$IMAGE_BASE_URL${it.backdropPath}").into(subPoster)
                                    Glide.with(requireContext()).load("$IMAGE_BASE_URL${it.backdropPath}").into(posterTopBar)
                                    tvOverview.text = it.overview
                                    tvTitleDetail.text = it.title
                                    tvReleaseDate.text = it.releaseDate

                                }
                            }
                        }
                    }
                }
            }
        }
//        lifecycleScope.launch {
//            detailMoviesViewModel.getDetailMovie(
//                token = "Bearer ${BuildConfig.API_KEY}", movieId = movieId, language = "en-US").collectLatest {
//                Log.d("observeDetailMovie", "judul = ${it?.data?.title}" )
//                if (it != null) {
//                    when(it){
//                        is Resource.Error -> {
//                            Log.d("observeDetailMovie", "MASUK ERROR" )
//                        }
//                        is Resource.Loading -> {
//
//                        }
//                        is Resource.Success -> {
//                            Log.d("observeDetailMovie", "MASUK Success" )
//                            it.data?.let {
//                                Log.d("detailMovie", "judul = ${it.title.toString()}" )
//                                Glide.with(requireContext()).load("$IMAGE_BASE_URL${it.backdropPath}").into(subPoster)
//                                Glide.with(requireContext()).load("$IMAGE_BASE_URL${it.backdropPath}").into(posterTopBar)
//                                tvOverview.text = it.overview
//                                tvTitleDetail.text = it.title
//                                tvReleaseDate.text = it.releaseDate
//
//                            }
//                        }
//                    }
//
//                }
//
//            }
//        }


//        lifecycleScope.launchWhenStarted {
//            detailMoviesViewModel.detailMovie.collectLatest {
//                Log.d("observeDetailMovie", "judul = ${it?.data?.title}" )
//                it?.let { response ->
//                    when(response){
//                        is Resource.Error -> {
//                            Log.d("observeDetailMovie", "MASUK ERROR" )
//                        }
//                        is Resource.Loading -> {
//
//                        }
//                        is Resource.Success -> {
//                            Log.d("observeDetailMovie", "MASUK Success" )
//                            response.data?.let {
//                                Log.d("detailMovie", "judul = ${it.title.toString()}" )
//                                Glide.with(requireContext()).load("$IMAGE_BASE_URL${it.backdropPath}").into(subPoster)
//                                Glide.with(requireContext()).load("$IMAGE_BASE_URL${it.backdropPath}").into(posterTopBar)
//                                tvOverview.text = it.overview
//                                tvTitleDetail.text = it.title
//                                tvReleaseDate.text = it.releaseDate
//
//                            }
//                        }
//                    }
//                }
//            }
//        }

    }
}