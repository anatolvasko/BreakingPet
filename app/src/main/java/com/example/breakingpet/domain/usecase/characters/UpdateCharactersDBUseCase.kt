package com.example.breakingpet.domain.usecase.characters

import android.util.Log
import com.example.breakingpet.domain.repository.CharactersRepository
import javax.inject.Inject

class UpdateCharactersDBUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun updateDatabase(): Unit {
            charactersRepository.updateDatabase()
    }

}