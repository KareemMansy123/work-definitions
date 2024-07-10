package com.example.worddefinitions.data.remote

import com.example.worddefinitions.data.remote.model.WordDefinition
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApiService {
    @GET("api/v2/entries/en/{word}")
    suspend fun getWordDefinition(@Path("word") word: String): List<WordDefinition>
}