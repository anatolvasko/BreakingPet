package com.example.breakingpet.domain.usecase

import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.domain.repository.EpisodesRepository
import javax.inject.Inject

class GetEpisodesListUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) {

    suspend fun getEpisodesList() : ArrayList<Episode>{
        return episodesRepository.getEpisodesList()
    }

}