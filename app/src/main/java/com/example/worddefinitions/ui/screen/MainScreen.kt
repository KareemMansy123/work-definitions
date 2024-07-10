package com.example.worddefinitions.ui.screen

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.worddefinitions.data.local.WordDatabase
import com.example.worddefinitions.data.remote.RetrofitInstance
import com.example.worddefinitions.ui.theme.Red
import com.example.worddefinitions.ui.vm.WordViewModel
import com.example.worddefinitions.ui.vm.factory.WordViewModelFactory
import com.example.worddefinitions.ui.widgets.ErrorMessage
import com.example.worddefinitions.ui.widgets.LoadingIndicator
import com.example.worddefinitions.ui.widgets.SearchInput
import com.example.worddefinitions.ui.widgets.TopBar
import com.example.worddefinitions.ui.widgets.WordDefinitionCard
import java.util.Locale

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val wordDao = WordDatabase.getDatabase(context).wordDao()
    val apiService = RetrofitInstance.api

    val factory = WordViewModelFactory(context.applicationContext as Application, wordDao, apiService)
    val viewModel: WordViewModel = viewModel(factory = factory)

    var word by remember { mutableStateOf("") }
    val wordDefinition by viewModel.wordDefinition.observeAsState()
    val error by viewModel.error.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)

    Scaffold(
        topBar = { TopBar("Dictionary App") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter a word to get its definition",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            SearchInput(word = word, onWordChange = { word = it }) {
                viewModel.fetchWordDefinition(word)
            }

            if (isLoading) {
                LoadingIndicator()
            }

            error?.let {
                ErrorMessage(it)
            }

            wordDefinition?.let {
                WordDefinitionCard(it)
            } ?: if (!isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "No definition found.",
                        fontSize = 16.sp,
                        color = Red
                    )
                }
            } else {

            }
        }
    }
}