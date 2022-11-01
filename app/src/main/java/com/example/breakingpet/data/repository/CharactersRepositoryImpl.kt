package com.example.breakingpet.data.repository

import android.util.Log
import com.example.breakingpet.data.database.dao.CharactersDao
import com.example.breakingpet.data.network.CharactersApi
import com.example.breakingpet.data.network.RequestCharacters
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao
) : CharactersRepository {
    private val requestCharacters = RequestCharacters()

    override suspend fun getCharactersList(): ArrayList<Character> {
        val charactersList = ArrayList<Character>()

        requestUsingOkhttp(charactersList)

        //requestUsingRetrofit(charactersList)

        saveToDatabase(charactersList)

        return charactersList
    }

    private fun saveToDatabase(charactersList: ArrayList<Character>){
        charactersDao.clearTable()

        for (item in charactersList){
            charactersDao.insertCharacter(item.toCharacterEntity())
        }
    }

    private fun requestUsingOkhttp(charactersList: ArrayList<Character>){
        val responseObj = requestCharacters.getResponseObj()

        if (responseObj.size>0){
            for(item in responseObj){
                if (item.category.contains("Breaking Bad")){
                    charactersList.add(item)
                }
            }
        }
    }

    private suspend fun requestUsingRetrofit(charactersList: ArrayList<Character>) {
        val response = charactersApi.getCharactersListResponse()

        if( response.isSuccessful){
            for (item in response.body()!!) {
                if (item.category == "Breaking Bad") {
                    charactersList.add(item)
                }
            }
        }
        else Log.d("ERROR", "${response.body()}")
    }

}