package com.example.breakingpet.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingpet.domain.model.quotes.Quote
import com.example.breakingpet.domain.usecase.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {

    val randomQuote = MutableLiveData<Quote>()

    fun getRandomQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            randomQuote.postValue(getRandomQuoteUseCase.getRandomQuote())
        }
    }
}