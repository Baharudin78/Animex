package com.baharudin.animex.data.model.song

data class SongResponse(
    val album: String,
    val anime_id: Int,
    val artist: String,
    val duration: Int,
    val id: Int,
    val local_spotify_url: String,
    val open_spotify_url: String,
    val preview_url: String,
    val season: Int,
    val title: String,
    val type: Int,
    val year: Int
)