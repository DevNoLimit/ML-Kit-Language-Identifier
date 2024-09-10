package com.devnolimit.languageidentifymlkit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    var userText by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize().padding(10.dp)
    ) {
        OutlinedTextField(
            value = userText,
            onValueChange = {
                userText = it
            },
            placeholder = {
                Text("Enter your text")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(10.dp))
        Button(onClick = {
            CustomLanguageModel.predictMultipleLanguage(userText){
                language = this
            }
        }) {
            Text("Predict Language", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        }
        Spacer(Modifier.size(10.dp))

        AnimatedVisibility(
            visible = language.isNotEmpty()
        ) {
            Text(language, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
}