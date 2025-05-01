package com.example.buddypage_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buddypage_demo.ui.theme.Buddypage_demoTheme
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import kotlinx.coroutines.delay
import androidx.compose.runtime.saveable.rememberSaveable




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Buddypage_demoTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController = navController, startDestination = "splash") {
                            composable("splash") { SplashScreen(navController) }
                            composable("home") {
                                Greeting(
                                    name = "User",
                                    onNavigateToGraphs = { navController.navigate("graphs") },
                                    onNavigateToClock = { navController.navigate("clock") },
                                    onNavigateToBed = { navController.navigate("bed") }
                                )
                            }
                            composable("graphs") { Graphs() }
                            composable("clock") { ClockScreen() }
                            composable("bed") { BedScreen() }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    onNavigateToGraphs: () -> Unit,
    onNavigateToClock: () -> Unit,
    onNavigateToBed: () -> Unit
) {
    val teddyQuotes = listOf(
        "Don't forget to set the alarm! I need to know when to wake up!",
        "My bed is so comfy! I always have good dreams on it.",
        "You're doing a great job sleeping!",
        "I love updating the corkboard! Helps me see how your sleep was!"
    )

    var currentMessage by remember { mutableStateOf<String?>(null) }
    var messageTimestamp by remember { mutableStateOf(0L) }
    var welcomeMessageVisible by rememberSaveable { mutableStateOf(true) }
    var teddyHealth by remember { mutableStateOf(0.75f) } // placeholder value from 0.0 to 1.0

    // Auto-hide bubbles
    LaunchedEffect(messageTimestamp) {
        delay(5000)
        // Only clear if still the most recent message
        if (System.currentTimeMillis() - messageTimestamp >= 5000) {
            currentMessage = null
        }
    }

    LaunchedEffect(Unit) {
        delay(5000)
        welcomeMessageVisible = false
    }

    Box(modifier = modifier.fillMaxSize()) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.bedroom),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Corkboard
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(125.dp)
                .offset(x = 15.dp, y = 250.dp)
                .clickable { onNavigateToGraphs() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.corkboard),
                contentDescription = "corkboard",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(scaleX = 2.6f, scaleY = 2.6f)
            )
        }

        // 🧪 Teddy Health Bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 25.dp) // adjust vertical position as needed
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Teddy's Energy", color = Color.White)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(Color(0xFFB7A4A4), shape = RoundedCornerShape(12.dp))
                    .padding(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(teddyHealth)
                        .background(Color(0xFF8FFFA2), shape = RoundedCornerShape(10.dp))
                )
            }

            Text(
                text = when {
                    teddyHealth > 0.7f -> "😄"
                    teddyHealth > 0.4f -> "😐"
                    else -> "😴"
                },
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Bed
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(125.dp)
                .offset(x = 250.dp, y = 510.dp)
                .clickable { onNavigateToBed() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.bed),
                contentDescription = "bed",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(scaleX = 2.9f, scaleY = 2.9f)
            )
        }

        // Clock
        Box(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp)
                .offset(x = 52.dp, y = 109.dp)
                .clickable { onNavigateToClock() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.clock),
                contentDescription = "clock",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(scaleX = 1.3f, scaleY = 1.3f)
            )
        }

        // Teddy
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(139.dp)
                .offset(x = 50.dp, y = 600.dp)
                .clickable {
                    currentMessage = teddyQuotes.random()
                    messageTimestamp = System.currentTimeMillis() // store time to track which message is active
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.teddy),
                contentDescription = "buddy",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(scaleX = 3.9f, scaleY = 3.9f)
            )
        }

        // Teddy's speech bubble
        AnimatedVisibility(
            visible = currentMessage != null,
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .offset(x = 180.dp, y = 570.dp)
                    .padding(4.dp)
                    .widthIn(max = 220.dp)
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(text = currentMessage ?: "", color = Color.Black)
            }
        }

        // Welcome message
        AnimatedVisibility(
            visible = welcomeMessageVisible,
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .offset(x = 140.dp, y = 520.dp)
                    .padding(4.dp)
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(text = "Hi $name! Welcome back!", color = Color.Black)
            }
        }
    }
}
