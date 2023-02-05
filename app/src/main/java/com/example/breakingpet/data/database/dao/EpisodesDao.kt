package com.example.breakingpet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.data.database.entities.EpisodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodes: List<EpisodeEntity>)

    @Query( "SELECT * FROM episodes" )
    fun getAllEpisodes() : Flow<List<EpisodeEntity>>

    @Query("DELETE FROM episodes")
    suspend fun deleteAllEpisodes()

}