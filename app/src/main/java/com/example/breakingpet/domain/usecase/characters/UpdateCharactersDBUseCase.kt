package com.example.breakingpet.domain.usecase.characters

import com.example.breakingpet.domain.repository.CharactersRepository
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.utils.Resource
import javax.inject.Inject

class UpdateCharactersDBUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun updateCharacterDatabase(): Resource<List<Character>> {
        return try {
            charactersRepository.updateCharactersDatabase()
            Resource.Loading()
        } catch (e: Exception){
            Resource.Error(e )
        }
    }
}