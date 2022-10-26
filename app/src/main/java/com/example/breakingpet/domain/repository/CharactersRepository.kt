package com.example.breakingpet.domain.repository

import com.example.breakingpet.domain.models.Character

interface CharactersRepository {

    suspend fun getCharactersList(): List<Character>

}