package com.baharudin.animex.data.local.dao

import androidx.room.*
import com.baharudin.animex.data.model.anime.AnimeResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorit(animeResponse: AnimeResponse)

    @Query("SELECT * FROM anime_list")
    suspend fun getAllFavorit() : Flow<List<AnimeResponse>>

    @Delete
    suspend fun deleteFavorit(animeResponse: AnimeResponse)
}