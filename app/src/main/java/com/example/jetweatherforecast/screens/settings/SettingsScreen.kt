package com.example.jetweatherforecast.screens.settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController,
settingsViewModel: SettingsViewModel = hiltViewModel()) {
    Text(text = "Settings...")
}