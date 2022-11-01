package com.example.breakingpet.domain.usecase

import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun getCharactersList(): ArrayList<Character> {
        return charactersRepository.getCharactersList()
    }

}