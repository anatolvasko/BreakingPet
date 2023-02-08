package com.example.breakingpet.domain.usecase.characters

import android.util.Log
import com.example.breakingpet.domain.repository.CharactersRepository
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.utils.Resource
import javax.inject.Inject

class UpdateCharactersDBUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun updateDatabase(): Resource<List<Character>> {
        return try {
            charactersRepository.updateDatabase()
            Resource.Loading(arrayListOf())
        } catch (e: Exception){
            Resource.Error(e , arrayListOf())
        }



    }

}