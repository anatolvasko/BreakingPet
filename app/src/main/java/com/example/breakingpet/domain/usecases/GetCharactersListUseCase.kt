package com.example.breakingpet.domain.usecases

import com.example.breakingpet.domain.models.Character
import com.example.breakingpet.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun getCharactersList(): List<Character> {
        return charactersRepository.getCharactersList()
    }

}