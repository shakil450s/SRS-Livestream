package com.srs.livestream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SRSHomeScreen()
        }
    }
}

@Composable
fun SRSHomeScreen() {
    val blue = Color(0xFF1769FF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FF))
            .padding(16.dp)
    ) {
        Text(
            text = "SRS LIVESTREAM",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = blue
        )

        Text(
            text = "Connect • Stream • Share",
            color = Color.Gray,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF1769FF),
                            Color(0xFF6B35E8)
                        )
                    ),
                    RoundedCornerShape(22.dp)
                )
                .padding(22.dp)
        ) {
            Column {
                Text(
                    "Welcome to SRS!",
                    color = Color.White,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Go live, connect with friends and share moments.",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Live Rooms",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "No live rooms available right now.",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            )
        ) {
            Text("Explore SRS LIVESTREAM")
        }
    }
}
