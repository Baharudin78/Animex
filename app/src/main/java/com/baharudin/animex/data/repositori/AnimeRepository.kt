package com.baharudin.animex.data.repositori

import com.baharudin.animex.data.model.anime.AnimeResponse
import retrofit2.Response

interface AnimeRepository {
    suspend fun getListAnime() : Response<AnimeResponse>
}