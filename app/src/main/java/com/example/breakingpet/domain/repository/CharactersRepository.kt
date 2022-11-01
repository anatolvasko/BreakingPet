package com.example.breakingpet.domain.repository

import com.example.breakingpet.domain.model.characters.Character

interface CharactersRepository {

    suspend fun getCharactersList(): ArrayList<Character>

}