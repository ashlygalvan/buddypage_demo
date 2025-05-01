package com.example.buddypage_demo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Enables splash screen support (API 31+)
        installSplashScreen()

        super.onCreate(savedInstanceState)

        // Optional: delay for 2 seconds (not required)
        // Thread.sleep(2000)

        // Start main activity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
