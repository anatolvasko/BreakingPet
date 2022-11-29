package com.example.breakingpet.data.network.characters

import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.domain.model.characters.Character
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {

    @GET("characters")
    suspend fun getAllCharacters() : List<CharacterEntity>

    companion object {
        const val BASE_URL = "https://www.breakingbadapi.com/api/"
    }

}
