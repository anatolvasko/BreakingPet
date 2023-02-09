package com.example.breakingpet.domain.model.episodes

import com.example.breakingpet.data.database.entities.EpisodeEntity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Episode(
    @SerializedName("id")
    val episodeID: Int,
    val title: String,
    val season: String,
    @SerializedName("air_date")
    val airDate: String,
    val characters: List<String>,
    val episode: String,
    val series: String,
    val description: String,
    val rating: String,
    val img: String
) : Serializable {

    fun toEpisodeEntity() = EpisodeEntity(
        episodeID = episodeID,
        title = title,
        season = season,
        airDate = airDate,
        characters = characters,
        episode = episode,
        series = series,
        description = description,
        rating = rating,
        img = img
    )
}