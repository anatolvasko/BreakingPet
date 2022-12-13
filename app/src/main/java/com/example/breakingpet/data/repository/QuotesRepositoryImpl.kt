package com.example.breakingpet.data.repository

import com.example.breakingpet.data.network.quotes.QuotesApi
import com.example.breakingpet.domain.model.quotes.Quote
import com.example.breakingpet.domain.repository.QuotesRepository
import javax.inject.Inject
import kotlin.random.Random

class QuotesRepositoryImpl @Inject constructor(
    private val quotesApi: QuotesApi
) : QuotesRepository {

    override suspend fun getRandomQuote(): Quote {
        val randomInt = Random.nextInt(1, 52)
        return quotesApi.getRandomQuote()[randomInt]
    }

}