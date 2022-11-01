package com.example.breakingpet.domain.model.episodes

import com.example.breakingpet.data.database.entities.EpisodeEntity
import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("episode_id")
    val episodeID: Long,
    val title: String,
    val season: String,
    @SerializedName("air_date")
    val airDate: String,
    val characters: List<String>,
    val episode: String,

) {

    fun toEpisodeEntity() = EpisodeEntity(
        episodeID = episodeID,
        title = title,
        season = season,
        airDate = airDate,
        characters = characters,
        episode = episode
    )

}