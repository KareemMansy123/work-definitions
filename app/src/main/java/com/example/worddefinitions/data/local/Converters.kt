package com.example.worddefinitions.data.local

import androidx.room.TypeConverter
import com.example.worddefinitions.data.local.model.DefinitionEntity
import com.example.worddefinitions.data.local.model.MeaningEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromMeaningEntityList(value: List<MeaningEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<MeaningEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMeaningEntityList(value: String): List<MeaningEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<MeaningEntity>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromDefinitionEntityList(value: List<DefinitionEntity>): String {
        val gson = Gson()
        val type = object : TypeToken<List<DefinitionEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toDefinitionEntityList(value: String): List<DefinitionEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<DefinitionEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}