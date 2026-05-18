package com.example.surya_shaktisolarmonitor.ui.screens.heritage

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatueFinderScreen(navController: NavController) {
    var isSearching by remember { mutableStateOf(false) }
    var nearestMemorial by remember { mutableStateOf<String?>(null) }
    var distance by remember { mutableStateOf<String?>(null) }

    val infiniteTransition = rememberInfiniteTransition(label = "searching")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotation"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hero Memorial Finder", color = MaterialTheme.colorScheme.primary) },
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isSearching) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .rotate(rotation),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Searching for nearest Hero Memorial...",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            } else if (nearestMemorial != null) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Memorial Found!",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    nearestMemorial!!,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    "Distance: $distance",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                
                Spacer(modifier = Modifier.height(48.dp))
                
                Button(
                    onClick = { 
                        isSearching = false
                        nearestMemorial = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Search Again")
                }
            } else {
                Text(
                    "Use GPS to find Solar Hero memorials near you.",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        isSearching = true
                        // Simulate a search delay
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black)
                ) {
                    Text("Find Nearest Memorial", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }

    LaunchedEffect(isSearching) {
        if (isSearching) {
            delay(3000)
            nearestMemorial = "Kempanna Solar Memorial, Bengaluru"
            distance = "2.4 km away"
            isSearching = false
        }
    }
}
