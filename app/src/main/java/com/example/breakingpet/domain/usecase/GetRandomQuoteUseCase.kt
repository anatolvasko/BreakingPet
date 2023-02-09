package com.example.breakingpet.domain.usecase

import com.example.breakingpet.domain.model.quotes.Quote
import com.example.breakingpet.domain.repository.EpisodesRepository
import com.example.breakingpet.domain.repository.QuotesRepository
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val quotesRepository: QuotesRepository
) {

    suspend fun getRandomQuote(): Quote {
        return quotesRepository.getRandomQuote()
    }
}