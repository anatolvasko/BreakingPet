package com.example.breakingpet.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = true)
    val idPrimary: Int? = null,
    @ColumnInfo(name = "episode_id")
    val episodeID: Long,
    val title: String,
    val season: String,
    @ColumnInfo(name = "air_date")
    val airDate: String,
    val characters: List<String>,
    val episode: String
)
