package com.example.breakingpet.domain.repository

import com.example.breakingpet.domain.model.quotes.Quote

interface QuotesRepository {

    suspend fun getRandomQuote(): Quote

}