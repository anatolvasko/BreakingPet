package com.example.breakingpet.data.network

import com.example.breakingpet.domain.model.characters.CharacterResponse
import com.example.breakingpet.domain.model.episodes.EpisodeResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class RequestEpisodes {

    fun getResponseObj() : EpisodeResponse {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

        val gson = Gson()

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .get()
            .url("https://breakingbadapi.com/api/episodes/")
            .build()

        val call = client.newCall(request)

        val response = call.execute()

        if (response.isSuccessful) {
            val stringJSON = response.body!!.string()
            return gson.fromJson(stringJSON, EpisodeResponse::class.java)
        } else {
            return EpisodeResponse()
        }
    }

}