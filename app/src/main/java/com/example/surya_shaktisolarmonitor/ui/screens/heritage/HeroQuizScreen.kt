package com.example.surya_shaktisolarmonitor.ui.screens.heritage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
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

data class Question(val text: String, val options: List<String>, val correctAnswer: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroQuizScreen(navController: NavController) {
    val questions = listOf(
        Question("What is the primary source of solar energy?", listOf("The Sun", "The Wind", "Coal", "Water"), 0),
        Question("Who is considered a 'Solar Hero'?", listOf("Someone who wastes power", "A clean energy advocate", "A coal miner", "None of these"), 1),
        Question("Solar panels convert sunlight into...?", listOf("Heat only", "Water", "Electricity", "Sound"), 2)
    )

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hero Quiz", color = MaterialTheme.colorScheme.primary) },
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
            if (!showResult) {
                val question = questions[currentQuestionIndex]
                
                Text(
                    "Question ${currentQuestionIndex + 1}/3",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    question.text,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                question.options.forEachIndexed { index, option ->
                    Button(
                        onClick = {
                            if (index == question.correctAnswer) {
                                score++
                            }
                            if (currentQuestionIndex < questions.size - 1) {
                                currentQuestionIndex++
                            } else {
                                showResult = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.DarkGray,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(option, fontSize = 18.sp)
                    }
                }
            } else {
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    "Quiz Completed!",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                
                Text(
                    "Your Score: $score/${questions.size}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                if (score == questions.size) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("CONGRATULATIONS!", color = Color.Black, fontWeight = FontWeight.Black)
                            Text("You earned the", color = Color.Black)
                            Text("HERITAGE BADGE", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                } else {
                    Text(
                        "Try again to earn the Heritage Badge!",
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black)
                ) {
                    Text("Finish")
                }
            }
        }
    }
}
