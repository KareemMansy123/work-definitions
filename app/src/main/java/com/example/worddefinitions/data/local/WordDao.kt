package com.example.worddefinitions.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.worddefinitions.data.local.model.WordDefinitionEntity

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordDefinition(wordDefinition: WordDefinitionEntity)

    @Query("SELECT * FROM worddefinition WHERE word = :word LIMIT 1")
    suspend fun getWordDefinition(word: String): WordDefinitionEntity?
}