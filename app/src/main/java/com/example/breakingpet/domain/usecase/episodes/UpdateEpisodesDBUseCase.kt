package com.example.breakingpet.domain.usecase.episodes

import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.domain.repository.EpisodesRepository
import com.example.breakingpet.utils.Resource
import javax.inject.Inject

class UpdateEpisodesDBUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) {

    suspend fun updateEpisodesDatabase(): Resource<List<Episode>> {
        return try {
            episodesRepository.updateEpisodesDatabase()
            Resource.Loading()
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}