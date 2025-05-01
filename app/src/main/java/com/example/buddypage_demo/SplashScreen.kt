package com.example.buddypage_demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment
import com.example.buddypage_demo.R

@Composable
fun SplashScreen(navController: NavController) {
    // Delay and then navigate
    LaunchedEffect(Unit) {
        delay(2000) // Show for 2 seconds
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true } // remove splash from backstack
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.background), // replace with your actual file name
            contentDescription = "Splash Screen",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}