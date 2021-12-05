package com.baharudin.animex.data.remote

import javax.inject.Inject

class RemoteAnimeSource @Inject constructor(
    private val animeApi: AnimeApi
) {
    suspend fun getAnimeList(page : Int) =
        animeApi.getAnimeList(page)
}