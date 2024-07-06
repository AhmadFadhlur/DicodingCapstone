package com.ashoka.core.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ashoka.core.R
import com.ashoka.core.data.remote.response.search.ResultsSearchItem
import com.ashoka.core.databinding.ListSearchMovieBinding
import com.ashoka.core.utils.EndPointMovie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class SearchMovieAdapter(var onClickItem: ((ResultsSearchItem) -> Unit?)? = null) :
    PagingDataAdapter<ResultsSearchItem, SearchMovieAdapter.ViewHolder>(callback) {

    companion object {
        val callback = object : DiffUtil.ItemCallback<ResultsSearchItem>() {
            override fun areItemsTheSame(
                oldItem: ResultsSearchItem,
                newItem: ResultsSearchItem
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ResultsSearchItem,
                newItem: ResultsSearchItem
            ): Boolean = oldItem == newItem
        }
    }

    class ViewHolder(private val binding: ListSearchMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResultsSearchItem) = with(binding) {
            title.text = item.title.toString()
            tvOriginTitle.text = item.originalTitle.toString()
            tvDate.text = item.releaseDate.toString()
            popularity.text = itemView.context.getString(R.string.popularity_d, item.popularity.toString())
            userScore.text = item.voteCount.toString()
            Glide.with(itemView.context).load("${EndPointMovie.IMAGE_BASE_URL}${item.posterPath}")
                .listener(object : RequestListener<Drawable> {
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
                }).into(imgPoster)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) holder.bind(data)

        holder.itemView.setOnClickListener {
            data?.let { onClickItem?.invoke(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListSearchMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}
