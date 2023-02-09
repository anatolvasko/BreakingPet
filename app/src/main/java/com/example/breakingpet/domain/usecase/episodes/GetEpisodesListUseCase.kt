package com.example.breakingpet.domain.usecase.episodes

import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.domain.repository.EpisodesRepository
import com.example.breakingpet.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodesListUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) {

    fun getEpisodesList() : Flow<Resource<List<Episode>>> {
        return episodesRepository.getEpisodesList()
    }
}