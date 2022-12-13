package com.example.breakingpet.domain.model.quotes

import com.example.breakingpet.data.database.entities.QuoteEntity
import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("id")
    val quoteId: Int,
    val quote: String,
    val author: String,
    val series: String
) {

    fun toQuoteEntity() = QuoteEntity(
        quoteId = quoteId,
        quote = quote,
        author = author,
        series = series
    )

}