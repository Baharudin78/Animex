package com.baharudin.animex.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.baharudin.animex.data.model.anime.AnimeDataResponse
import com.baharudin.animex.data.model.anime.AnimeResponse
import com.baharudin.animex.data.remote.AnimeApi
import com.baharudin.animex.data.remote.RemoteAnimeSource
import com.baharudin.animex.util.Constants.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException


private const val ANIME_STARTING_PAGE_INDEX = 1
class AnimePagingSource(
    private var animeDataResponse: RemoteAnimeSource
) : PagingSource<Int, AnimeResponse>(){
    override fun getRefreshKey(state: PagingState<Int, AnimeResponse>): Int? {
        return state.anchorPosition?.let {  anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse> {
        val pageIndex = params.key ?: ANIME_STARTING_PAGE_INDEX
        return try {
            val response = animeDataResponse.getAnimeList(pageIndex)
            val anime = response.body()!!.animeResponse
            val nextKey =
                if (anime.isEmpty()) {
                    null
                }else {
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page (
                data = anime,
                prevKey = if (pageIndex == ANIME_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
                    )
        }catch (e : IOException) {
            return LoadResult.Error(e)
        }catch (e : HttpException) {
            return LoadResult.Error(e)
        }
    }

}