package com.ashoka.capstonedicoding.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashoka.capstonedicoding.databinding.LoadingStateMovieAdapterBinding

class LoadingStateMovieAdapter (private val retry : () -> Unit) :
    LoadStateAdapter<LoadingStateMovieAdapter.LoadingStateViewHolder>(){

    class LoadingStateViewHolder(private val binding : LoadingStateMovieAdapterBinding, retry: () -> Unit) :
       RecyclerView.ViewHolder(binding.root){
//
       fun bind (loadState: LoadState)= with(binding){
           if (loadState is LoadState.Error){
              errorMsg.text = loadState.error.localizedMessage
            }
            pbLoadNews.isVisible = loadState is LoadState.Loading
           retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
       }
   }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        TODO("Not yet implemented")
    }
}

