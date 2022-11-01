package com.example.breakingpet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.data.database.entities.EpisodeEntity

@Dao
interface EpisodesDao {

    @Insert
    fun insertEpisode(episodeEntity: EpisodeEntity)

    @Query( "SELECT * FROM episodes" )
    fun getAllEpisodes() : List<EpisodeEntity>

    @Query("DELETE FROM episodes")
    fun clearTable()

}