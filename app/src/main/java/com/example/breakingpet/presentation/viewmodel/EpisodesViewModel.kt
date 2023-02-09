package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.breakingpet.domain.usecase.episodes.GetEpisodesListUseCase
import com.example.breakingpet.domain.usecase.episodes.UpdateEpisodesDBUseCase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getEpisodesListUseCase: GetEpisodesListUseCase,
    private val updateEpisodesDBUseCase: UpdateEpisodesDBUseCase
) : ViewModel() {

    private val _allEpisodes = getEpisodesListUseCase.getEpisodesList().asLiveData() as MutableLiveData
    val allEpisodes = _allEpisodes

    fun updateEpisodesDatabase() {
        viewModelScope.launch {
            _allEpisodes.value = updateEpisodesDBUseCase.updateEpisodesDatabase()
        }
    }
}