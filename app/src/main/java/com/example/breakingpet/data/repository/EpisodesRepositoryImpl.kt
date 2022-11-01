package com.example.breakingpet.data.repository

import com.example.breakingpet.data.database.dao.EpisodesDao
import com.example.breakingpet.data.network.RequestEpisodes
import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.domain.repository.EpisodesRepository
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(
    private val episodesDao: EpisodesDao
) : EpisodesRepository {
    private val requestEpisodes = RequestEpisodes()

    override suspend fun getEpisodesList(): ArrayList<Episode> {
        val episodesList = ArrayList<Episode>()

        val responseObj = requestEpisodes.getResponseObj()

        if (responseObj.size>0){
            for(item in responseObj){
                if (item.episodeID<=62){
                    episodesList.add(item)
                }
            }
        }

        return episodesList
    }
}