package com.example.worddefinitions.data.local.maper

import com.example.worddefinitions.data.local.model.DefinitionEntity
import com.example.worddefinitions.data.local.model.MeaningEntity
import com.example.worddefinitions.data.local.model.WordDefinitionEntity
import com.example.worddefinitions.data.remote.model.Definition
import com.example.worddefinitions.data.remote.model.Meaning
import com.example.worddefinitions.data.remote.model.WordDefinition

// Mapping from Remote models to Local entities
fun WordDefinition.toEntityModel(): WordDefinitionEntity {
    return WordDefinitionEntity(
        word = this.word,
        meanings = this.meanings.map { it.toEntityModel() }
    )
}

fun Meaning.toEntityModel(): MeaningEntity {
    return MeaningEntity(
        partOfSpeech = this.partOfSpeech,
        definitions = this.definitions.map { it.toEntityModel() }
    )
}

fun Definition.toEntityModel(): DefinitionEntity {
    return DefinitionEntity(
        definition = this.definition,
        example = this.example
    )
}

// Mapping from Local entities to Remote models
fun WordDefinitionEntity.toRemoteModel(): WordDefinition {
    return WordDefinition(
        word = this.word,
        meanings = this.meanings.map { it.toRemoteModel() }
    )
}

fun MeaningEntity.toRemoteModel(): Meaning {
    return Meaning(
        partOfSpeech = this.partOfSpeech,
        definitions = this.definitions.map { it.toRemoteModel() }
    )
}

fun DefinitionEntity.toRemoteModel(): Definition {
    return Definition(
        definition = this.definition,
        example = this.example
    )
}