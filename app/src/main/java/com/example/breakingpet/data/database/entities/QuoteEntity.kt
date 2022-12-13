package com.example.breakingpet.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.breakingpet.domain.model.quotes.Quote
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val idPrimary: Int? = null,
    @SerializedName("id")
    val quoteId: Int,
    val quote: String,
    val author: String,
    val series: String
) {

    fun toQuote() = Quote(
        quoteId = quoteId,
        quote = quote,
        author = author,
        series = series
    )

}