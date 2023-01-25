package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.breakingpet.domain.usecase.GetEpisodesListUseCase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
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