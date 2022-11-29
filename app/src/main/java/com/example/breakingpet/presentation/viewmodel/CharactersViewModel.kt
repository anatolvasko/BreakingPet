package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.breakingpet.domain.usecase.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    private val _allCharacters = getCharactersListUseCase.getCharactersList().asLiveData()
    val allCharacters = _allCharacters


    fun getCharactersList() {
        val a = getCharactersListUseCase.getCharactersList().asLiveData()
    }

}