package com.baharudin.animex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baharudin.animex.databinding.AnimeLoadStateFooterBinding

class AnimeLoadStateAdapter(private val retry : () -> Unit)
    : LoadStateAdapter<AnimeLoadStateAdapter.LoadStateHolder>(){
    override fun onBindViewHolder(
        holder: AnimeLoadStateAdapter.LoadStateHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): AnimeLoadStateAdapter.LoadStateHolder {
        val inflated = AnimeLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return LoadStateHolder(inflated)
    }

    inner class LoadStateHolder(val binding : AnimeLoadStateFooterBinding) :
            RecyclerView.ViewHolder(binding.root) {
                init {
                    binding.buttonRetry.setOnClickListener {
                        retry.invoke()
                    }
                }
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
            }


}