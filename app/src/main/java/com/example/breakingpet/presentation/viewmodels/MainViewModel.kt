package com.example.breakingpet.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingpet.domain.models.Character
import com.example.breakingpet.domain.usecases.GetCharactersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase
) : ViewModel() {

    //splash screen
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    var allCharacters = MutableLiveData<List<Character>>()

    fun getCharactersList() {
        viewModelScope.launch {
            allCharacters.postValue(getCharactersListUseCase.getCharactersList())
        }
    }


    //splash screen
    init {
        viewModelScope.launch {
            delay(500)
            _isLoading.value = false
        }
    }
}