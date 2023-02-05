package com.example.breakingpet.domain.usecase.characters

import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.repository.CharactersRepository
import com.example.breakingpet.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersListUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    fun getCharactersList(): Flow<Resource<List<Character>>> {
        return charactersRepository.getCharactersList()
    }



}