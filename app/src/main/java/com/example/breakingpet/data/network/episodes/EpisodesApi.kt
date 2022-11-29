package com.example.breakingpet.data.network.episodes

import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.data.database.entities.EpisodeEntity
import retrofit2.http.GET

interface EpisodesApi {

    @GET("episodes")
    suspend fun getAllEpisodes() : List<EpisodeEntity>

    companion object {
        const val BASE_URL = "https://www.breakingbadapi.com/api/"
    }

}