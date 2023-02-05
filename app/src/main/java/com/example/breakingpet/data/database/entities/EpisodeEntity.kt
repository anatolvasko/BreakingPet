package com.example.breakingpet.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.breakingpet.domain.model.episodes.Episode
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true)
    val idPrimary: Int? = null,
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
) {

    fun toEpisode() : Episode {
        return Episode(
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

}
