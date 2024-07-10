package com.example.worddefinitions.data.repository

import com.example.worddefinitions.data.local.WordDao
import com.example.worddefinitions.data.local.maper.toEntityModel
import com.example.worddefinitions.data.local.maper.toRemoteModel
import com.example.worddefinitions.data.remote.DictionaryApiService
import com.example.worddefinitions.data.remote.model.WordDefinition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordRepository(
    private val apiService: DictionaryApiService,
    private val wordDao: WordDao
) {
    suspend fun getWordDefinition(word: String): WordDefinition? {
        return try {
            val response = apiService.getWordDefinition(word)
            val wordDefinition = response.firstOrNull()
            if (wordDefinition != null) {
                withContext(Dispatchers.IO) {
                    wordDao.insertWordDefinition(wordDefinition.toEntityModel())
                }
            }
            wordDefinition
        } catch (e: Exception) {
            withContext(Dispatchers.IO) {
                wordDao.getWordDefinition(word)?.toRemoteModel()
            }
        }
    }
}