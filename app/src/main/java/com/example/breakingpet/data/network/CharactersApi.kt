package com.example.breakingpet.data.network

import com.example.breakingpet.domain.models.Character
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {

    @GET("/characters")
    suspend fun getCharactersListResponse() : Response<List<Character>>

}