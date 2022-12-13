package com.example.breakingpet.data.network.quotes

import com.example.breakingpet.domain.model.quotes.Quote
import retrofit2.http.GET

interface QuotesApi {

    @GET("quotes/")
    suspend fun getRandomQuote(): List<Quote>

}