package com.example.breakingpet.domain.repository

import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

     fun getCharactersList(): Flow<Resource<List<Character>>>

}