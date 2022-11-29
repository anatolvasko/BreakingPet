package com.example.breakingpet.data.di

import com.example.breakingpet.data.database.AppDataBase
import com.example.breakingpet.data.database.dao.CharactersDao
import com.example.breakingpet.data.network.characters.CharactersApi
import com.example.breakingpet.data.repository.CharactersRepositoryImpl
import com.example.breakingpet.domain.repository.CharactersRepository
import com.example.breakingpet.domain.usecase.GetCharactersListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterModule {

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersListUseCase {
        return GetCharactersListUseCase(charactersRepository = charactersRepository)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(
        charactersApi: CharactersApi,
        charactersDao: CharactersDao
    ): CharactersRepository {
        return CharactersRepositoryImpl(
            charactersApi = charactersApi,
            charactersDao = charactersDao
        )
    }

    @Provides
    @Singleton
    fun provideCharactersApi(retrofit: Retrofit) : CharactersApi =
        retrofit.create(CharactersApi::class.java)

    @Provides
    @Singleton
    fun provideCharactersDao(appDataBase: AppDataBase): CharactersDao {
        return appDataBase.getCharactersDao()
    }

}