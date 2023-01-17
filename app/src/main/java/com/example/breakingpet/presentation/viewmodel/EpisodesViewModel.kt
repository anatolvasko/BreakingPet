package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.breakingpet.databinding.FragmentEpisodesBinding
import com.example.breakingpet.domain.model.episodes.Episode
import com.example.breakingpet.domain.usecase.GetEpisodesListUseCase
import com.example.breakingpet.presentation.fragments.episodes.EpisodesFragmentDirections
import com.example.breakingpet.presentation.recyclerview.EpisodeAdapter
import com.example.breakingpet.utils.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val getEpisodesListUseCase: GetEpisodesListUseCase
) : ViewModel() {

    private val _allEpisodes = getEpisodesListUseCase.getEpisodesList().asLiveData()
    val allEpisodes = _allEpisodes

     val mDataBase = Firebase.firestore
    val imagesUrlList = ArrayList<String>()

    suspend fun getImageList() {
        imagesUrlList.clear()
        withContext(Dispatchers.IO) {
            mDataBase.collection("posters")
                .get()
                .addOnSuccessListener {
                    for (document in it) {
                        for (i in 1..62) {
                            val imageUrl = document.data[i.toString()]
                            imagesUrlList.add(imageUrl as String)
                        }
                    }
                }.await()
        }
    }


}