package com.baharudin.animex.data.local.converter

import androidx.room.TypeConverter
import com.baharudin.animex.data.model.anime.Descriptions
import com.baharudin.animex.data.model.anime.Titles

class Converter {
    @TypeConverter
    fun fromTitle(titles: Titles) : String {
        return titles.en
    }
    @TypeConverter
    fun toTitle(en : String) : Titles {
        return Titles(en)
    }

    @TypeConverter
    fun descriptionConverter(descriptions: Descriptions) : String {
        return descriptions.en
    }
    @TypeConverter
    fun descriptionToString(en: String) : Descriptions {
        return Descriptions(en)
    }

 }