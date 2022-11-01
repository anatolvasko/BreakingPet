package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.usecase.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    var allCharacters = MutableLiveData<ArrayList<Character>>()

    fun getCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            allCharacters.postValue(getCharactersListUseCase.getCharactersList())
        }
    }

}