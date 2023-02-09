package com.example.breakingpet.data.di

import com.example.breakingpet.data.database.AppDataBase
import com.example.breakingpet.data.database.dao.EpisodesDao
import com.example.breakingpet.data.network.episodes.EpisodesApi
import com.example.breakingpet.data.repository.EpisodesRepositoryImpl
import com.example.breakingpet.domain.repository.CharactersRepository
import com.example.breakingpet.domain.repository.EpisodesRepository
import com.example.breakingpet.domain.usecase.characters.UpdateCharactersDBUseCase
import com.example.breakingpet.domain.usecase.episodes.GetEpisodesListUseCase
import com.example.breakingpet.domain.usecase.episodes.UpdateEpisodesDBUseCase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpisodesModule {

    @Provides
    @Singleton
    fun provideGetEpisodesUseCase(episodesRepository: EpisodesRepository): GetEpisodesListUseCase {
        return GetEpisodesListUseCase(episodesRepository = episodesRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateEpisodesDBUseCase(episodesRepository: EpisodesRepository) : UpdateEpisodesDBUseCase {
        return UpdateEpisodesDBUseCase(episodesRepository = episodesRepository)
    }

    @Provides
    @Singleton
    fun provideEpisodesRepository(
        episodesDao: EpisodesDao,
        episodesApi: EpisodesApi
    ): EpisodesRepository {
        return EpisodesRepositoryImpl(
            episodesDao = episodesDao,
            episodesApi = episodesApi
        )
    }

    @Provides
    @Singleton
    fun provideEpisodesApi(retrofit: Retrofit) : EpisodesApi =
        retrofit.create(EpisodesApi::class.java)

    @Provides
    @Singleton
    fun provideEpisodesDao(appDataBase: AppDataBase): EpisodesDao {
        return appDataBase.getEpisodesDao()
    }

    @Provides
    @Singleton
    fun provideStorageReference(): StorageReference {
        return FirebaseStorage.getInstance().reference
    }

}