package com.example.surya_shaktisolarmonitor.ui.screens.heritage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class StoryPage(val titleEn: String, val textEn: String, val titleKn: String, val textKn: String)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun IllustratedStoryScreen(navController: NavController) {
    var isKannada by remember { mutableStateOf(false) }
    val pages = listOf(
        StoryPage(
            "The Sun's Power", "Long ago, people realized the sun could light up the world.",
            "ಸೂರ್ಯನ ಶಕ್ತಿ", "ಬಹಳ ಹಿಂದೆಯೇ, ಜನರು ಸೂರ್ಯನು ಜಗತ್ತನ್ನು ಬೆಳಗಿಸಬಹುದು ಎಂದು ಅರಿತುಕೊಂಡರು."
        ),
        StoryPage(
            "The First Hero", "Kempanna was the first to use solar energy in his village.",
            "ಮೊದಲ ವೀರ", "ಕೆಂಪಣ್ಣನವರು ತಮ್ಮ ಗ್ರಾಮದಲ್ಲಿ ಸೌರಶಕ್ತಿಯನ್ನು ಬಳಸಿದ ಮೊದಲ ವ್ಯಕ್ತಿ."
        ),
        StoryPage(
            "Legacy of Light", "Today, we carry forward the legacy of clean energy.",
            "ಬೆಳಕಿನ ಪರಂಪರೆ", "ಇಂದು, ನಾವು ಶುದ್ಧ ಇಂಧನದ ಪರಂಪರೆಯನ್ನು ಮುಂದುವರಿಸುತ್ತಿದ್ದೇವೆ."
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Illustrated Story", color = MaterialTheme.colorScheme.primary) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                },
                actions = {
                    TextButton(onClick = { isKannada = !isKannada }) {
                        Text(if (isKannada) "English" else "ಕನ್ನಡ", color = MaterialTheme.colorScheme.primary)
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
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                val page = pages[pageIndex]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Placeholder for illustration
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("ILLUSTRATION", color = Color.Black, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = if (isKannada) page.titleKn else page.titleEn,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (isKannada) page.textKn else page.textEn,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                    
                    Spacer(modifier = Modifier.height(48.dp))
                    
                    Text(
                        "Swipe to continue (${pageIndex + 1}/${pages.size})",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
