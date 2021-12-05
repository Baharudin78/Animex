package com.baharudin.animex.data.remote

import com.baharudin.animex.data.model.anime.AnimeDataResponse
import com.baharudin.animex.data.model.anime.AnimeResponse
import com.baharudin.animex.util.Constants.Companion.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    @GET("$BASE_URL/v1/anime")
    suspend fun getAnimeList(
        @Query("page") page : Int
    ) : Response<AnimeDataResponse>
}