package com.example.breakingpet.data.repository

import com.example.breakingpet.data.network.quotes.QuotesApi
import com.example.breakingpet.domain.model.quotes.Quote
import com.example.breakingpet.domain.repository.QuotesRepository
import com.example.breakingpet.utils.BreakingConstants.ERROR
import com.example.breakingpet.utils.BreakingConstants.QUOTES_COUNT
import javax.inject.Inject
import kotlin.random.Random

class QuotesRepositoryImpl @Inject constructor(
    private val quotesApi: QuotesApi
) : QuotesRepository {

    override suspend fun getRandomQuote(): Quote {

        return try {
            quotesApi.getRandomQuote()[Random.nextInt(1, QUOTES_COUNT)]
        } catch (e: Exception) {
            Quote(1, ERROR, "", "")
        }
    }

}