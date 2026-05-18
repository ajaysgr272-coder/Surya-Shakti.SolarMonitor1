package com.example.surya_shaktisolarmonitor.ui.screens.heritage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DistrictMapScreen(navController: NavController) {
    val districts = listOf("Bengaluru", "Mysuru", "Hubballi", "Belagavi", "Mangaluru")
    var selectedDistrict by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("District Heroes Map", color = MaterialTheme.colorScheme.primary) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Tap a district to find Solar Heroes",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Simulated Map View
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.DarkGray)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    districts.forEach { district ->
                        Button(
                            onClick = { selectedDistrict = district },
                            modifier = Modifier.fillMaxWidth().padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedDistrict == district) MaterialTheme.colorScheme.primary else Color.Gray,
                                contentColor = if (selectedDistrict == district) Color.Black else Color.White
                            )
                        ) {
                            Icon(Icons.Default.LocationOn, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text(district)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            selectedDistrict?.let { district ->
                Text(
                    "Solar Heroes in $district:",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                
                LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                    val heroes = when(district) {
                        "Bengaluru" -> listOf("Solar Park Pioneer A", "Energy Saver B")
                        "Mysuru" -> listOf("Palace Solar Guard C", "Heritage Light D")
                        else -> listOf("Local Solar Hero X", "Green Warrior Y")
                    }
                    items(heroes) { hero ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                        ) {
                            Text(
                                hero,
                                modifier = Modifier.padding(16.dp),
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}
