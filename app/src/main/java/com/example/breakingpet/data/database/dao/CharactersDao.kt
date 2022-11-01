package com.example.breakingpet.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.breakingpet.data.database.entities.CharacterEntity

@Dao
interface CharactersDao {

    @Insert
    fun insertCharacter(characterEntity: CharacterEntity)

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): List<CharacterEntity>

    @Query("DELETE FROM characters")
    fun clearTable()

}