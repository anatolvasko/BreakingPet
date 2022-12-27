package com.example.breakingpet.data.repository

import android.content.Context
import android.widget.Toast
import com.example.breakingpet.data.network.quotes.QuotesApi
import com.example.breakingpet.domain.model.quotes.Quote
import com.example.breakingpet.domain.repository.QuotesRepository
import javax.inject.Inject
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

class QuotesRepositoryImpl @Inject constructor(
    private val quotesApi: QuotesApi
) : QuotesRepository {

    override suspend fun getRandomQuote(): Quote {

        return try{
            quotesApi.getRandomQuote()[Random.nextInt(1, 52)]
        }catch (e: Exception){
            Quote(1, "Error", "", "")
        }

    }

}