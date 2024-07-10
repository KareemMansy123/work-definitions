package com.example.worddefinitions.ui.vm.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worddefinitions.data.local.WordDao
import com.example.worddefinitions.data.remote.DictionaryApiService
import com.example.worddefinitions.ui.vm.WordViewModel

class WordViewModelFactory(
    private val application: Application,
    private val wordDao: WordDao,
    private val apiService: DictionaryApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(application, wordDao, apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}