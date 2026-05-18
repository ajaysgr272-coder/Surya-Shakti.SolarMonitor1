package com.example.surya_shaktisolarmonitor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.surya_shaktisolarmonitor.ui.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController, viewModel: MainViewModel) {
    val logs by viewModel.logs.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daily History", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFC107))
            )
        },
        containerColor = Color(0xFF121212)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(logs) { log ->
                val dateStr = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(log.date))
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(dateStr, color = Color(0xFFFFC107), fontWeight = FontWeight.Bold, modifier = Modifier.width(60.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row {
                                Text("Gen: ", color = Color.Gray, fontSize = 12.sp)
                                Text("${log.generatedKwh} kWh", color = Color.White, fontSize = 14.sp)
                            }
                            Row {
                                Text("Con: ", color = Color.Gray, fontSize = 12.sp)
                                Text("${log.consumedKwh} kWh", color = Color.White, fontSize = 14.sp)
                            }
                        }
                        Text("₹${"%.0f".format(log.savingsInInr)}", color = Color.Green, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
