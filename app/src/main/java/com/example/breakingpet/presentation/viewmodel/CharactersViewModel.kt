package com.example.breakingpet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.example.breakingpet.domain.usecase.characters.GetCharactersListUseCase
import com.example.breakingpet.domain.usecase.characters.UpdateCharactersDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val updateCharactersDBUseCase: UpdateCharactersDBUseCase
) : ViewModel() {

    private var _allCharacters = getCharactersListUseCase.getCharactersList().asLiveData()
    var allCharacters = _allCharacters


    fun updateDatabase() {

        viewModelScope.launch {
            try {
                updateCharactersDBUseCase.updateDatabase()
            } catch (e: Exception) {
                Log.d("TAG", e.message.toString())
            }
        }
    }


}