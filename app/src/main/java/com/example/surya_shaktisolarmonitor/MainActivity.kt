package com.example.surya_shaktisolarmonitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.surya_shaktisolarmonitor.ui.navigation.NavGraph
import com.example.surya_shaktisolarmonitor.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

// Solar Theme based on your Architecture Diagram
private val YellowPrimary = Color(0xFFFFC107)
private val BlackBackground = Color(0xFF121212)

private val HighContrastColorScheme = darkColorScheme(
    primary = YellowPrimary,
    onPrimary = Color.Black,
    background = BlackBackground,
    onBackground = YellowPrimary,
    surface = BlackBackground,
    onSurface = YellowPrimary
)

@Composable
fun SuryaShaktiTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = HighContrastColorScheme, content = content)
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuryaShaktiTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}