package com.example.surya_shaktisolarmonitor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.surya_shaktisolarmonitor.ui.navigation.Screen
import com.example.surya_shaktisolarmonitor.ui.viewmodel.MainViewModel

@Composable
fun DashboardScreen(navController: NavHostController, viewModel: MainViewModel) {
    val logs by viewModel.logs.collectAsState()
    val latestLog = logs.lastOrNull()

    // Calculate dynamic Green Score
    val scoreValue = if (latestLog != null && latestLog.consumedKwh > 0) {
        ((latestLog.generatedKwh / latestLog.consumedKwh) * 100).coerceIn(0.0, 100.0).toInt()
    } else if (logs.isNotEmpty()) {
        78 // Default for demo if we have logs but latest is 0
    } else {
        0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Dark background from your screenshot
            .padding(16.dp)
    ) {
        Text(
            text = "Dashboard",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // 1. Green Score Ring
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            CircularProgressIndicator(
                progress = scoreValue / 100f,
                modifier = Modifier.size(160.dp),
                color = Color(0xFFFFC107), // Yellow from diagram
                strokeWidth = 12.dp,
                trackColor = Color(0xFF333333)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Green Score:", color = Color.White, fontSize = 14.sp)
                Text(scoreValue.toString(), color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 2. Data Input Cards
        val genText = latestLog?.let { "Last Generation: +${it.generatedKwh} kWh" } ?: "No generation logged"
        val consText = latestLog?.let { "Last Consumption: -${it.consumedKwh} kWh" } ?: "No consumption logged"
        
        DataCard(genText)
        Spacer(modifier = Modifier.height(8.dp))
        DataCard(consText)

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Peak Sun Alert (Yellow Box from Diagram)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Black),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFC107))
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.WbSunny, contentDescription = null, tint = Color(0xFFFFC107))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Peak Sun Alert: High Sun!", color = Color(0xFFFFC107), fontWeight = FontWeight.Bold)
                    Text("Perfect time for heavy appliances.", color = Color.Gray, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // 4. Heritage Module (Buttons from your Screenshot)
        Text("Heritage Module", color = Color(0xFFFFC107), fontWeight = FontWeight.Bold)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = { navController.navigate(Screen.DistrictMap.route) },
                modifier = Modifier.weight(1f).border(1.dp, Color(0xFFFFC107), RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) { Text("District Map", color = Color(0xFFFFC107)) }

            Button(
                onClick = { navController.navigate(Screen.IllustratedStory.route) },
                modifier = Modifier.weight(1f).border(1.dp, Color(0xFFFFC107), RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) { Text("Story Book", color = Color(0xFFFFC107)) }

            FloatingActionButton(
                onClick = { navController.navigate(Screen.GenerationLog.route) },
                containerColor = Color(0xFFFFC107),
                modifier = Modifier.size(48.dp)
            ) { Icon(Icons.Default.Add, contentDescription = null) }
        }
    }
}

@Composable
fun DataCard(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Text(text, modifier = Modifier.padding(16.dp), color = Color.Black, fontWeight = FontWeight.Medium)
    }
}