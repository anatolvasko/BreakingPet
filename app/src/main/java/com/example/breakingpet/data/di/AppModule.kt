package com.example.breakingpet.data.di

import android.content.Context
import androidx.room.Room
import com.example.breakingpet.data.database.AppDataBase
import com.example.breakingpet.data.database.dao.CharactersDao
import com.example.breakingpet.data.database.dao.EpisodesDao
import com.example.breakingpet.data.network.CharactersApi
import com.example.breakingpet.data.repository.CharactersRepositoryImpl
import com.example.breakingpet.data.repository.EpisodesRepositoryImpl
import com.example.breakingpet.domain.repository.CharactersRepository
import com.example.breakingpet.domain.repository.EpisodesRepository
import com.example.breakingpet.domain.usecase.GetCharactersListUseCase
import com.example.breakingpet.domain.usecase.GetEpisodesListUseCase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersListUseCase {
        return GetCharactersListUseCase(charactersRepository = charactersRepository)
    }

    @Provides
    @Singleton
    fun provideGetEpisodesUseCase(episodesRepository: EpisodesRepository): GetEpisodesListUseCase {
        return GetEpisodesListUseCase(episodesRepository = episodesRepository)
    }

    @Provides
    @Singleton
    fun provideEpisodesRepository(
        episodesDao: EpisodesDao
    ): EpisodesRepository {
        return EpisodesRepositoryImpl(
            episodesDao = episodesDao
        )
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
    fun baseUrl() = "https://www.breakingbadapi.com/api/"

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "database.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(appDataBase: AppDataBase): CharactersDao {
        return appDataBase.getCharactersDao()
    }

    @Provides
    @Singleton
    fun provideEpisodesDao(appDataBase: AppDataBase): EpisodesDao {
        return appDataBase.getEpisodesDao()
    }

}