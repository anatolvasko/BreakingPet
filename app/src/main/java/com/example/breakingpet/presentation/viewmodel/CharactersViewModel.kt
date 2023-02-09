package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
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

    private val _allCharacters =
        getCharactersListUseCase.getCharactersList().asLiveData() as MutableLiveData
    val allCharacters = _allCharacters

    fun updateCharacterDatabase() {
        viewModelScope.launch {
            _allCharacters.value = updateCharactersDBUseCase.updateCharacterDatabase()
        }
    }
}