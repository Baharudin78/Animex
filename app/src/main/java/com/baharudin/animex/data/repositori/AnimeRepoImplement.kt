package com.baharudin.animex.data.repositori

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.baharudin.animex.data.local.dao.AnimeDao
import com.baharudin.animex.data.model.anime.AnimeDataResponse
import com.baharudin.animex.data.model.anime.AnimeResponse
import com.baharudin.animex.data.remote.BaseApiResponse
import com.baharudin.animex.data.remote.RemoteAnimeSource
import com.baharudin.animex.paging.AnimePagingSource
import com.baharudin.animex.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class AnimeRepoImplement @Inject constructor(
    private val remoteAnimeSource: RemoteAnimeSource,
    private var animeDao: AnimeDao
) : BaseApiResponse() {

    fun getAnimeList() : Flow<PagingData<AnimeResponse>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {AnimePagingSource(remoteAnimeSource)}
    ).flow


    suspend fun insertFavorit(animeResponse: AnimeResponse) : NetworkResult<String> {
        return try {
            animeDao.insertFavorit(animeResponse)
            NetworkResult.Success("Anime in favorit")
        }catch (e : Exception) {
            e.printStackTrace()
            NetworkResult.Error("something wrong ", e.message)
        }
    }
    suspend fun deleteFavorit(animeResponse: AnimeResponse) {
        try {
            animeDao.deleteFavorit(animeResponse)
            return
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }
}