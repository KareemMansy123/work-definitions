package com.example.worddefinitions.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "worddefinition")
data class WordDefinitionEntity(
    @PrimaryKey val word: String,
    val meanings: List<MeaningEntity>
)

data class MeaningEntity(
    val partOfSpeech: String,
    val definitions: List<DefinitionEntity>
)

data class DefinitionEntity(
    val definition: String,
    val example: String?
)