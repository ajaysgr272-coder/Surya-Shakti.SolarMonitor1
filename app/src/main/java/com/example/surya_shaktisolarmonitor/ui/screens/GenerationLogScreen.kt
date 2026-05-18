package com.example.surya_shaktisolarmonitor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.surya_shaktisolarmonitor.ui.navigation.Screen
import com.example.surya_shaktisolarmonitor.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerationLogScreen(navController: NavController, viewModel: MainViewModel) {
    val profile by viewModel.profile.collectAsState()
    var generatedKwh by remember { mutableStateOf("") }
    var weather by remember { mutableStateOf("Sunny") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Log Daily Generation", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFC107))
            )
        },
        containerColor = Color(0xFF121212)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("How was the weather today?", color = Color.White)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                FilterChip(
                    selected = weather == "Sunny",
                    onClick = { weather = "Sunny" },
                    label = { Text("Sunny ☀️") }
                )
                FilterChip(
                    selected = weather == "Cloudy",
                    onClick = { weather = "Cloudy" },
                    label = { Text("Cloudy ☁️") }
                )
            }

            Button(
                onClick = {
                    val capacity = profile?.panelCapacityKw ?: 0.0
                    val simulated = if (weather == "Sunny") capacity * 0.9 else capacity * 0.4
                    generatedKwh = simulated.toString()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text("Simulate based on Weather", color = Color.White)
            }

            OutlinedTextField(
                value = generatedKwh,
                onValueChange = { generatedKwh = it },
                label = { Text("Generated kWh Today") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFFC107),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFFFFC107),
                    unfocusedLabelColor = Color.Gray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Button(
                onClick = {
                    // Navigate to consumption tracker after logging generation
                    navController.navigate("${Screen.ConsumptionTracker.route}/$generatedKwh/$weather")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107), contentColor = Color.Black)
            ) {
                Text("Next: Consumption", fontWeight = FontWeight.Bold)
            }
        }
    }
}
