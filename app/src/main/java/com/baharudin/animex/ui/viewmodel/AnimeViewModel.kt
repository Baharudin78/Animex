package com.baharudin.animex.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.baharudin.animex.data.model.anime.AnimeDataResponse
import com.baharudin.animex.data.model.anime.AnimeResponse
import com.baharudin.animex.data.repositori.AnimeRepoImplement
import com.baharudin.animex.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepoImplement: AnimeRepoImplement,
    application : Application
) : AndroidViewModel(application) {

    private lateinit var animeCurrentItem : Flow<PagingData<AnimeResponse>>

    fun animeList() : Flow<PagingData<AnimeResponse>>{
        val animeResult : Flow<PagingData<AnimeResponse>> =
            animeRepoImplement.getAnimeList().cachedIn(viewModelScope)
        animeCurrentItem = animeResult
        return animeResult
    }

}