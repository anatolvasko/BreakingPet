package com.example.breakingpet.data.repository

import android.util.Log
import com.example.breakingpet.data.database.dao.CharactersDao
import com.example.breakingpet.data.database.entities.CharacterEntity
import com.example.breakingpet.data.network.characters.CharactersApi
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.repository.CharactersRepository
import com.example.breakingpet.utils.Resource
import com.example.breakingpet.utils.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao
) : CharactersRepository {


    override fun getCharactersList(): Flow<Resource<List<Character>>> {

        return networkBoundResource(
            query = {
                toCharactersList(charactersDao.getAllCharacters())
            },
            fetch = {
                delay(2000 )
                charactersApi.getAllCharacters().map { it.toCharacter() }
            },
            saveFetchResult = { characters ->
                charactersDao.deleteAllCharacters()
                charactersDao.insertCharacters(characters.map { it.toCharacterEntity() })
            },
            shouldFetch = {
                it.isNotEmpty()
            }
        )
    }

    override suspend fun updateDatabase() {
        val list = charactersApi.getAllCharacters()
        charactersDao.deleteAllCharacters()
        charactersDao.insertCharacters(list)

    }

    private fun toCharactersList(list: Flow<List<CharacterEntity>>): Flow<List<Character>> {
        return list.map { listCharactersEntity ->
            listCharactersEntity.map { characterEntity ->
                characterEntity.toCharacter()
            }.filter {
                it.category.contains("Breaking Bad")
            }
        }
    }

}