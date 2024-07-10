package com.example.worddefinitions.ui.vm

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.worddefinitions.data.local.WordDao
import com.example.worddefinitions.data.local.maper.toRemoteModel
import com.example.worddefinitions.data.remote.DictionaryApiService
import com.example.worddefinitions.data.remote.model.WordDefinition
import com.example.worddefinitions.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(
    application: Application,
    private val wordDao: WordDao,
    private val apiService: DictionaryApiService
) : AndroidViewModel(application) {

    private val wordRepository: WordRepository = WordRepository(apiService, wordDao)

    private val _wordDefinition = MutableLiveData<WordDefinition?>()
    val wordDefinition: LiveData<WordDefinition?> = _wordDefinition

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(ConnectivityManager::class.java)
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun fetchWordDefinition(word: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                if (isInternetAvailable()) {
                    val definition = wordRepository.getWordDefinition(word)
                    _wordDefinition.postValue(definition)
                    _error.postValue(null)
                } else {
                    val cachedDefinition = wordDao.getWordDefinition(word)?.toRemoteModel()
                    if (cachedDefinition != null) {
                        _wordDefinition.postValue(cachedDefinition)
                    } else {
                        _error.postValue("No internet connection and no cached data available.")
                    }
                }
            } catch (e: Exception) {
                _error.postValue("Failed to fetch data: ${e.message}")
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}