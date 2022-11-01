package com.example.breakingpet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.breakingpet.data.database.dao.CharactersDao
import com.example.breakingpet.data.database.dao.EpisodesDao
import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.data.database.entities.ConverterIntList
import com.example.breakingpet.data.database.entities.ConverterStringList
import com.example.breakingpet.data.database.entities.EpisodeEntity


@Database(
    version = 1,
    entities = [CharacterEntity::class, EpisodeEntity::class]
    )
@TypeConverters(ConverterStringList::class, ConverterIntList::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getCharactersDao() : CharactersDao

    abstract fun getEpisodesDao() : EpisodesDao

}