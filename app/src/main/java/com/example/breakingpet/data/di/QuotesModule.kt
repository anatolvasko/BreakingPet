package com.example.breakingpet.data.di

import com.example.breakingpet.data.network.quotes.QuotesApi
import com.example.breakingpet.data.repository.QuotesRepositoryImpl
import com.example.breakingpet.domain.repository.QuotesRepository
import com.example.breakingpet.domain.usecase.GetRandomQuoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuotesModule {

    @Provides
    @Singleton
    fun provideGetRandomQuoteUseCase(quotesRepository: QuotesRepository): GetRandomQuoteUseCase {
        return GetRandomQuoteUseCase(quotesRepository = quotesRepository)
    }

    @Provides
    @Singleton
    fun provideQuotesRepository(
        quotesApi: QuotesApi
    ): QuotesRepository{
        return QuotesRepositoryImpl(
            quotesApi = quotesApi
        )
    }

    @Provides
    @Singleton
    fun provideQuotesApi(retrofit: Retrofit) : QuotesApi =
        retrofit.create(QuotesApi::class.java)

}