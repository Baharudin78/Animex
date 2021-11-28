package com.baharudin.animex.data.model.episode

data class EpisodeResponse(
    val anime_id: Int,
    val id: Int,
    val locale: String,
    val number: Int,
    val source: String,
    val title: String,
    val video: String
)