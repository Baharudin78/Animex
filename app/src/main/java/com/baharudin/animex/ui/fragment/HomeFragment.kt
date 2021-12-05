package com.baharudin.animex.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.baharudin.animex.R
import com.baharudin.animex.adapter.AnimeAdapter
import com.baharudin.animex.adapter.AnimeLoadStateAdapter
import com.baharudin.animex.databinding.FragmentHomeBinding
import com.baharudin.animex.ui.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val animeViewModel: AnimeViewModel by activityViewModels()
    private lateinit var animeAdapter : AnimeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        animeAdapter = AnimeAdapter()

        binding.apply {
            rvAnime.adapter = animeAdapter.withLoadStateHeaderAndFooter(
                header = AnimeLoadStateAdapter{ animeAdapter.retry() },
                footer = AnimeLoadStateAdapter{ animeAdapter.retry() }
            )
            btRetry.setOnClickListener {
                animeAdapter.retry()
            }
            rvAnime.setHasFixedSize(true)

        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            animeViewModel.animeList().collectLatest {
                animeAdapter.submitData(it)
            }
        }
        animeAdapter.addLoadStateListener {loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                rvAnime.isVisible = loadState.source.refresh is LoadState.NotLoading
                btRetry.isVisible = loadState.source.refresh is LoadState.Error
                tvError.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        animeAdapter.itemCount < 1) {
                    rvAnime.isVisible = false
                    tvError.isVisible = true
                }else {
                    tvError.isVisible = false
                }

            }
        }
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}