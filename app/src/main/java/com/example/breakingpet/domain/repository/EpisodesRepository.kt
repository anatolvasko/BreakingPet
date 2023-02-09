package com.example.breakingpet.domain.repository

import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.utils.Resource
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    fun getEpisodesList(): Flow<Resource<List<Episode>>>

    suspend fun updateEpisodesDatabase()

}