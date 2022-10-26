package com.example.breakingpet.data.repository

import android.util.Log
import com.example.breakingpet.data.network.CharactersApi
import com.example.breakingpet.domain.models.Character
import com.example.breakingpet.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi
) : CharactersRepository {

    override suspend fun getCharactersList(): List<Character> {
        val charactersList = ArrayList<Character>()

        val response = charactersApi.getCharactersListResponse()

        if( response.isSuccessful){
            for (item in response.body()!!) {
                if (item.category == "Breaking Bad") {
                    charactersList.add(item)
                }
            }
        }
        else Log.d("ERROR", "${response.body()}")

        return charactersList
    }

}