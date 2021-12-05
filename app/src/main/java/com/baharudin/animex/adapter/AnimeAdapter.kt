package com.baharudin.animex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.animex.R
import com.baharudin.animex.data.model.anime.AnimeResponse
import com.baharudin.animex.databinding.ItemAnimeListBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import javax.inject.Inject

class AnimeAdapter : PagingDataAdapter<AnimeResponse, AnimeAdapter.AnimeViewHolder>(AnimeComparator) {

    inner class AnimeViewHolder(val binding : ItemAnimeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(animeResponse: AnimeResponse) {
            binding.apply {
                Glide.with(itemView)
                    .load(animeResponse.cover_image)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivPoster)
                tvJudul.text = animeResponse.titles.en
            }
        }
    }


    object AnimeComparator : DiffUtil.ItemCallback<AnimeResponse>() {
        override fun areItemsTheSame(oldItem: AnimeResponse, newItem: AnimeResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AnimeResponse, newItem: AnimeResponse): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: AnimeAdapter.AnimeViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bindItem(currentItem)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimeAdapter.AnimeViewHolder {
        val inflater = ItemAnimeListBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent, false
        )
        return AnimeViewHolder(inflater)
    }
}