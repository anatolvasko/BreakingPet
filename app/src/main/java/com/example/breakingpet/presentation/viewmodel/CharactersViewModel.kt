package com.example.breakingpet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.breakingpet.domain.model.characters.Character

import androidx.room.PrimaryKey
import com.example.breakingpet.domain.usecase.characters.GetCharactersListUseCase
import com.example.breakingpet.domain.usecase.characters.UpdateCharactersDBUseCase
import com.example.breakingpet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
    private val updateCharactersDBUseCase: UpdateCharactersDBUseCase
) : ViewModel() {

    private val _allCharacters =
        getCharactersListUseCase.getCharactersList().asLiveData() as MutableLiveData

    var allCharacters = _allCharacters


    fun updateDatabase() {
        //setValue() variant
       /* _allCharacters.value = getCharactersListUseCase.getCharactersList().asLiveData().value
        allCharacters = _allCharacters*/

        //postValue() variant
        viewModelScope.launch(Dispatchers.IO) {
            _allCharacters.postValue(
                getCharactersListUseCase.getCharactersList().asLiveData().value
                //Resource.Success(testList)
            )
            allCharacters = _allCharacters

        }

        //
        /*viewModelScope.launch {
            try {
                updateCharactersDBUseCase.updateDatabase()
            } catch (e: Exception) {
                Log.d("TAG", e.message.toString())
            }
        }*/
    }


    //list of Characters for testing
    val testList = listOf<Character>(Character(
        1,
        "Walter",
        "WW",
        "",
        "AA",
        listOf("AAA"),
        "Alive",
        "",
        "Breaking Bad",
        listOf(1, 2, 4)
    )
    )


    override fun onCleared() {
        Log.d("AAA", "private val: ${_allCharacters}")
        Log.d("AAA", "_allCharacters is null?: ${_allCharacters.value == null}")
        Log.d("AAA", "non private val:  ${allCharacters}")
        super.onCleared()

        Log.d("AAA", "onClearedCalled")
    }

}