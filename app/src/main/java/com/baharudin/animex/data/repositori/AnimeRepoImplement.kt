package com.baharudin.animex.data.repositori

import com.baharudin.animex.data.local.dao.AnimeDao
import com.baharudin.animex.data.model.anime.AnimeResponse
import com.baharudin.animex.data.remote.AnimeApi
import retrofit2.Response

class AnimeRepoImplement(
    private var animeApi: AnimeApi,
    private var animeDao: AnimeDao
) : AnimeRepository {
    override suspend fun getListAnime(): Response<AnimeResponse> {
        TODO()
    }
}