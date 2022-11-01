package com.example.breakingpet.domain.repository

import com.example.breakingpet.domain.model.episodes.Episode

interface EpisodesRepository {

    suspend fun getEpisodesList(): ArrayList<Episode>

}