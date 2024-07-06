package com.ashoka.core.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashoka.core.R
import com.ashoka.core.data.remote.response.discover.ResultsTvShowItem
import com.ashoka.core.databinding.ListTvshowsBinding
import com.ashoka.core.utils.EndPointMovie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class TvShowsAdapter (var onClickItem: ((ResultsTvShowItem) -> Unit?)? = null) :
    PagingDataAdapter<ResultsTvShowItem, TvShowsAdapter.ViewHolder>(callback){

    companion object {
        val callback = object : DiffUtil.ItemCallback<ResultsTvShowItem>() {

            override fun areItemsTheSame(
                oldItem: ResultsTvShowItem,
                newItem: ResultsTvShowItem
            ):
                    Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ResultsTvShowItem,
                newItem: ResultsTvShowItem
            ): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(var binding: ListTvshowsBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(item:ResultsTvShowItem) = with(binding){
                title.text = item.originalName.toString()
                Log.d("TvShowBindAdapter", "item title = ${item.originalName}")
                Log.d("TvShowBindAdapter", "item id = ${item.id}")
                tvOriginTitle.text = item.name.toString()
                tvDate.text = item.firstAirDate.toString()
                popularity.text = itemView.context.getString(R.string.popularity_d, item.popularity.toString())
                userScore.text = item.voteCount.toString()
                Glide.with(itemView.context).load("${EndPointMovie.IMAGE_BASE_URL}${item.posterPath}").listener(
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            shimmerPoster.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            shimmerPoster.visibility = View.GONE
                            return false
                        }
                    }
                ).into(imgPoster)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("onBindViewHolder", "holder = ${holder}")
        val data = getItem(position)
        if (data != null) holder.bind(data)  else Log.d("onBindViewHolder", "data = null")

        holder.itemView.setOnClickListener {
            if (data != null) {
                onClickItem?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListTvshowsBinding.inflate(LayoutInflater.from(parent.context), parent , false))

}