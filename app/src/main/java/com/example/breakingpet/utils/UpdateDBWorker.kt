package com.example.breakingpet.utils

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.breakingpet.data.database.dao.CharactersDao
import com.example.breakingpet.data.network.characters.CharactersApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class UpdateDbWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val charactersApi: CharactersApi,
    private val charactersDao: CharactersDao
) : CoroutineWorker(
    context,
    workerParams
) {
    override suspend fun doWork(): Result {


        val list = charactersApi.getAllCharacters()
        charactersDao.deleteAllCharacters()
        //charactersDao.insertCharacters(list)


        return Result.success()
    }


}