package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingpet.domain.model.characters.Character
import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.domain.usecase.GetEpisodesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getEpisodesListUseCase: GetEpisodesListUseCase
) : ViewModel(){

    var allEpisodes = MutableLiveData<ArrayList<Episode>>()

    fun getCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            allEpisodes.postValue(getEpisodesListUseCase.getEpisodesList())
        }
    }

}