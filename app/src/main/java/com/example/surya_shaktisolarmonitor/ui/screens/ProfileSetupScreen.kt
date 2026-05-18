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
fun ProfileSetupScreen(navController: NavController, viewModel: MainViewModel) {
    var name by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("") }
    var rate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile Setup", color = Color.Black) },
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
            Text("Complete your household setup", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Household Name") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFFC107),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFFFFC107),
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color(0xFFFFC107),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            OutlinedTextField(
                value = village,
                onValueChange = { village = it },
                label = { Text("Village / Area") },
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
                value = capacity,
                onValueChange = { capacity = it },
                label = { Text("Solar Panel Capacity (kW)") },
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
                value = rate,
                onValueChange = { rate = it },
                label = { Text("Per-unit rate (₹)") },
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
                    if (name.isNotBlank() && village.isNotBlank() && capacity.isNotBlank() && rate.isNotBlank()) {
                        viewModel.saveProfile(name, village, capacity.toDoubleOrNull() ?: 0.0, rate.toDoubleOrNull() ?: 0.0)
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.ProfileSetup.route) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107), contentColor = Color.Black)
            ) {
                Text("Save and Continue", fontWeight = FontWeight.Bold)
            }
        }
    }
}
