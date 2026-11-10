
package com.srs.livestream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
            SRSApp()
        }
    }
}

@Composable
fun SRSApp() {
    var page by remember { mutableStateOf("home") }

    when (page) {
        "home" -> HomeScreen(
            onLogin = { page = "login" },
            onSignup = { page = "signup" }
        )
        "login" -> AccountScreen("Login") {
            page = "home"
        }
        "signup" -> AccountScreen("Create Account") {
            page = "home"
        }
    }
}

@Composable
fun HomeScreen(
    onLogin: () -> Unit,
    onSignup: () -> Unit
) {
    val blue = Color(0xFF1769FF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FF))
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(35.dp))

        Text(
            "SRS LIVESTREAM",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = blue
        )

        Text(
            "Connect • Stream • Share",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(35.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(blue, Color(0xFF7138E8))
                    ),
                    RoundedCornerShape(25.dp)
                )
                .padding(25.dp)
        ) {
            Column {
                Text(
                    "Welcome to SRS!",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(10.dp))

                Text(
                    "Go live, connect with friends and share your moments.",
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(Modifier.height(35.dp))

        Button(
            onClick = onLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text("LOGIN", fontSize = 17.sp)
        }

        Spacer(Modifier.height(15.dp))

        OutlinedButton(
            onClick = onSignup,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                "CREATE ACCOUNT",
                fontSize = 17.sp,
                color = blue
            )
        }

        Spacer(Modifier.height(35.dp))

        Text(
            "Live Rooms",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        Text(
            "No live rooms available right now.",
            color = Color.Gray
        )

        Spacer(Modifier.weight(1f))

        Text(
            "SRS LIVESTREAM © 2026",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}

@Composable
fun AccountScreen(
    title: String,
    onBack: () -> Unit
) {
    val blue = Color(0xFF1769FF)
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F7FF))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(35.dp))

        Text(
            title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = blue
        )

        Spacer(Modifier.height(30.dp))

        if (title == "Create Account") {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(15.dp))
        }

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(25.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = blue
            )
        ) {
            Text(
                if (title == "Login") "LOGIN" else "SIGN UP"
            )
        }

        Spacer(Modifier.height(20.dp))

        TextButton(onClick = onBack) {
            Text("Back to Home")
        }
    }
}
