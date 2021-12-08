package com.baharudin.animex.data.remote

import com.baharudin.animex.data.model.anime.AnimeDataResponse
import com.baharudin.animex.data.model.anime.AnimeResponse
import com.baharudin.animex.data.model.movie.MovieReponse
import com.baharudin.animex.util.Constants.Companion.API_KEY
import com.baharudin.animex.util.Constants.Companion.BASE_URL
import com.baharudin.animex.util.Constants.Companion.MOVIE_BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface AnimeApi {
    @GET("$MOVIE_BASE_URL/movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlaying(
        @Query("page") pageNumber : Int
    ) : MovieReponse
    @GET("$BASE_URL/v1/anime")
    suspend fun getAnimeList(
        @Query("page") page : Int
    ) : AnimeDataResponse
}