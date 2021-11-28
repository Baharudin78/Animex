package com.baharudin.animex.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.baharudin.animex.data.local.converter.Converter
import com.baharudin.animex.data.local.dao.AnimeDao
import com.baharudin.animex.data.model.anime.AnimeResponse

@Database(
    entities = [AnimeResponse::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AnimeDatabase : RoomDatabase(){
    abstract fun animeDao() : AnimeDao
}