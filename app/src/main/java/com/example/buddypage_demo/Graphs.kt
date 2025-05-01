package com.example.buddypage_demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun Graphs() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.graph_data),
            contentDescription = "Graphs background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        // Graph image
        Column(
            modifier = Modifier
                .width(135.dp)
                .offset(x = 138.dp, y = 275.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.graph),
                contentDescription = "Graph",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .graphicsLayer(scaleX = 2.6f, scaleY = 2.6f)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Vertical text block with gray background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 470.dp)
                .padding(horizontal = 30.dp)
                .background(Color.White.copy(alpha = 0.8f), shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "REM Sleep: 2h 14m",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
                Text(
                    text = "Light Sleep: 3h 42m",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
                Text(
                    text = "Deep Sleep: 2h 49m",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
                Text(
                    text = "Total Sleep: 8h 45m",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
                Text(
                    text = "Average Sleep: 8h 45m",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Start
                )
            }
        }
    }
}