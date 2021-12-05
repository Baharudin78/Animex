package com.baharudin.animex.data.model.anime

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "anime_list")
data class AnimeResponse(
    val anilist_id: Int,
    val banner_image: String,
    val cover_color: String,
    val cover_image: String,
    val descriptions: Descriptions,
    val end_date: String,
    val episode_duration: Int,
    val episodes_count: Int,
    val format: Int,
//    val genres: List<String>,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val mal_id: Int,
    val score: Int,
    val season_period: Int,
    val season_year: Int,
    val start_date: String,
    val status: Int,
    val titles: Titles
) : Serializable