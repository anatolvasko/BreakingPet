package com.example.breakingpet.data.di

import android.content.Context
import androidx.room.Room
import com.example.breakingpet.data.database.AppDataBase
import com.example.breakingpet.data.database.dao.EpisodesDao
import com.example.breakingpet.data.network.characters.CharactersApi
import com.example.breakingpet.data.repository.EpisodesRepositoryImpl
import com.example.breakingpet.domain.repository.EpisodesRepository
import com.example.breakingpet.domain.usecase.GetEpisodesListUseCase
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
    fun baseUrl() = "https://6396667fa68e43e41802ec6a.mockapi.io/api/"

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "breaking_database"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    /*@Provides
    @Singleton
    fun provideStorageReference() : StorageReference {
        return FirebaseStorage.getInstance().reference
    }*/

}