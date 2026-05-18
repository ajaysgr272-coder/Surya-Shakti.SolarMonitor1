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
fun ConsumptionTrackerScreen(
    navController: NavController,
    viewModel: MainViewModel,
    generatedKwh: Double,
    weather: String
) {
    var previousReading by remember { mutableStateOf("") }
    var currentReading by remember { mutableStateOf("") }

    val consumed = (currentReading.toDoubleOrNull() ?: 0.0) - (previousReading.toDoubleOrNull() ?: 0.0)
    val netUsage = generatedKwh - consumed

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Consumption Tracker", color = Color.Black) },
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
            Text("Enter Meter Readings", color = Color.White, fontWeight = FontWeight.Bold)

            OutlinedTextField(
                value = previousReading,
                onValueChange = { previousReading = it },
                label = { Text("Previous Meter Reading (kWh)") },
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

            OutlinedTextField(
                value = currentReading,
                onValueChange = { currentReading = it },
                label = { Text("Current Meter Reading (kWh)") },
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

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Summary", color = Color(0xFFFFC107), fontWeight = FontWeight.Bold)
                    Text("Total Consumed: ${if (consumed >= 0) consumed else 0.0} kWh", color = Color.White)
                    Text("Net Usage: ${"%.2f".format(netUsage)} kWh", color = Color.White)
                    Text(
                        text = if (netUsage >= 0) "Over-generation: ${"%.2f".format(netUsage)} kWh (Exported)" 
                               else "Grid Dependency: ${"%.2f".format(-netUsage)} kWh",
                        color = if (netUsage >= 0) Color.Green else Color.Red
                    )
                }
            }

            Button(
                onClick = {
                    if (currentReading.isNotBlank() && previousReading.isNotBlank()) {
                        viewModel.logEnergy(generatedKwh, if (consumed >= 0) consumed else 0.0, weather)
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107), contentColor = Color.Black)
            ) {
                Text("Log Today's Energy", fontWeight = FontWeight.Bold)
            }
        }
    }
}
