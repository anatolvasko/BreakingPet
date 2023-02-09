package com.example.breakingpet.data.repository

import com.example.breakingpet.data.database.dao.EpisodesDao
import com.example.breakingpet.data.database.entities.EpisodeEntity
import com.example.breakingpet.data.network.episodes.EpisodesApi
import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.domain.repository.EpisodesRepository
import com.example.breakingpet.utils.Resource
import com.example.breakingpet.utils.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val episodesDao: EpisodesDao,
    private val episodesApi: EpisodesApi
) : EpisodesRepository {

    override fun getEpisodesList(): Flow<Resource<List<Episode>>> {

        return networkBoundResource(
            query = {
                toEpisodesList(episodesDao.getAllEpisodes())
            },
            fetch = {
                delay(2000)
                episodesApi.getAllEpisodes().map { it.toEpisode() }

            },
            saveFetchResult = { episodes ->
                episodesDao.deleteAllEpisodes()
                episodesDao.insertEpisodes(episodes.map { it.toEpisodeEntity() })
            }/*,
            shouldFetch = {
                it.isNotEmpty()
            }*/
        )
    }

    override suspend fun updateEpisodesDatabase() {
        val episodesList = episodesApi.getAllEpisodes()
        episodesDao.deleteAllEpisodes()
        episodesDao.insertEpisodes(episodesList)
    }

    private fun toEpisodesList(list: Flow<List<EpisodeEntity>>): Flow<List<Episode>> {
        return list.map { listEpisodesEntity ->
            listEpisodesEntity.map { episodeEntity ->
                episodeEntity.toEpisode()
            }.filter { it.series.contains("Breaking Bad") }
        }
    }

}