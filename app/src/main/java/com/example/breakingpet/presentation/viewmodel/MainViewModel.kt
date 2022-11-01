package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingpet.domain.usecase.GetCharactersListUseCase
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





    //splash screen
    init {
        viewModelScope.launch {
            delay(700)
            _isLoading.value = false
        }
    }
}