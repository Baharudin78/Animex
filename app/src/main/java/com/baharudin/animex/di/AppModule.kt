package com.baharudin.animex.di

import android.content.Context
import androidx.room.Room
import com.baharudin.animex.data.local.dao.AnimeDao
import com.baharudin.animex.data.local.database.AnimeDatabase
import com.baharudin.animex.data.remote.AnimeApi
import com.baharudin.animex.data.repositori.AnimeRepoImplement
import com.baharudin.animex.data.repositori.AnimeRepository
import com.baharudin.animex.util.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi() : Moshi = Moshi
        .Builder()
        .run {
            add(KotlinJsonAdapterFactory())
                .build()
        }

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(moshi: Moshi, okHttpClient: OkHttpClient) : AnimeApi =
        Retrofit
            .Builder()
            .run {
                baseUrl(BASE_URL)
                addConverterFactory(MoshiConverterFactory.create(moshi))
                client(okHttpClient)
                build()
            }.create(AnimeApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context : Context
    ) : AnimeDatabase = Room.databaseBuilder(
        context,
        AnimeDatabase::class.java,
        "anime"
    ).build()

    @Provides
    @Singleton
    fun provideAnimeDao(
        animeDatabase: AnimeDatabase
    ) = animeDatabase.animeDao()

    @Provides
    @Singleton
    fun provideRepository(
        animeApi: AnimeApi,
        animeDao: AnimeDao
    ) : AnimeRepository {
        return AnimeRepoImplement(
            animeApi,
            animeDao
        )
    }

}