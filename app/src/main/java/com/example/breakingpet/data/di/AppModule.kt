package com.example.breakingpet.data.di

import com.example.breakingpet.data.network.CharactersApi
import com.example.breakingpet.data.repository.CharactersRepositoryImpl
import com.example.breakingpet.domain.repository.CharactersRepository
import com.example.breakingpet.domain.usecases.GetCharactersListUseCase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = "https://www.breakingbadapi.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): CharactersApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CharactersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersListUseCase {
        return GetCharactersListUseCase(charactersRepository = charactersRepository)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(charactersApi: CharactersApi): CharactersRepository {
        return CharactersRepositoryImpl(charactersApi = charactersApi)
    }

}