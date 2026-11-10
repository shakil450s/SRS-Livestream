
package com.srs.livestream

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

private val Bg = Color(0xFF05051A)
private val Panel = Color(0xFF11102D)
private val Purple = Color(0xFF9B20FF)
private val Pink = Color(0xFFFF168B)
private val Cyan = Color(0xFF00D9FF)
private val White = Color.White
private val Muted = Color(0xFFAAA9C7)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SRSApp() }
    }
}

@Composable
fun SRSApp() {
    var page by remember { mutableStateOf("Welcome") }

    when (page) {
        "Welcome" -> WelcomeScreen(
            onLogin = { page = "Login" },
            onSignup = { page = "Create Account" },
            onExplore = { page = "Home" }
        )
        "Login" -> AccountScreen("Login") { page = "Home" }
        "Create Account" -> AccountScreen("Create Account") { page = "Home" }
        "Home" -> HomeScreen(page) { page = it }
        else -> SimplePage(page) { page = "Home" }
    }
}

@Composable
fun WelcomeScreen(
    onLogin: () -> Unit,
    onSignup: () -> Unit,
    onExplore: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF17002F), Bg, Color(0xFF090D30))
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("▶", color = Cyan, fontSize = 72.sp, fontWeight = FontWeight.Black)
        Text("SRS LIVESTREAM", color = White, fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold)
        Text("CONNECT • STREAM • SHARE", color = Cyan, fontSize = 12.sp)
        Spacer(Modifier.height(35.dp))
        Text("Your world. Your stage.", color = White, fontSize = 25.sp,
            fontWeight = FontWeight.Bold)
        Text("Meet people, share moments and enjoy live rooms.",
            color = Muted, fontSize = 14.sp)
        Spacer(Modifier.height(35.dp))
        Button(
            onClick = onLogin,
            modifier = Modifier.fillMaxWidth().height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Purple),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("LOGIN", fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(13.dp))
        OutlinedButton(
            onClick = onSignup,
            modifier = Modifier.fillMaxWidth().height(54.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("CREATE ACCOUNT", color = Cyan, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(18.dp))
        TextButton(onClick = onExplore) {
            Text("Explore as Guest", color = Muted)
        }
    }
}

@Composable
fun HomeScreen(page: String, navigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Bg)
    ) {
        Column(
            modifier = Modifier.weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("▶", color = Cyan, fontSize = 34.sp,
                    fontWeight = FontWeight.Black)
                Spacer(Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("SRS", color = White, fontSize = 29.sp,
                        fontWeight = FontWeight.ExtraBold)
                    Text("LIVESTREAM", color = Cyan, fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
                    Text("Connect • Stream • Share", color = Muted, fontSize = 10.sp)
                }
                Text("⌕", color = White, fontSize = 32.sp)
                Spacer(Modifier.width(15.dp))
                Text("♧", color = White, fontSize = 25.sp)
                Spacer(Modifier.width(12.dp))
                Text("👤", fontSize = 27.sp,
                    modifier = Modifier.clickable { navigate("Profile") })
            }

            Box(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF25005C), Color(0xFF7100A8), Color(0xFF090C48))
                        ), RoundedCornerShape(24.dp)
                    )
                    .border(1.dp, Purple, RoundedCornerShape(24.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Text("● LIVE EXPERIENCE", color = Pink,
                        fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Text("Go Live", color = White, fontSize = 35.sp,
                        fontWeight = FontWeight.ExtraBold)
                    Text("with SRS", color = Color(0xFFFFD52A),
                        fontSize = 27.sp, fontWeight = FontWeight.Bold)
                    Text("Share your talent with the world",
                        color = White, fontSize = 13.sp)
                    Spacer(Modifier.height(18.dp))
                    Button(
                        onClick = { navigate("Go Live") },
                        colors = ButtonDefaults.buttonColors(containerColor = Pink),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text("▶ START LIVE", color = White, fontWeight = FontWeight.Bold)
                    }
                }
                Text("✨", fontSize = 65.sp,
                    modifier = Modifier.align(Alignment.CenterEnd))
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 14.dp, vertical = 17.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickTile("👑", "Top Hosts")
                QuickTile("🔥", "Trending")
                QuickTile("🎁", "Gifts")
                QuickTile("🪙", "Recharge")
            }

            SectionTitle("📡 Live Now") { navigate("Discover") }
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
                    .padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LiveCard("👩🏻", "Music & Fun", "Maya Queen", "8.7K", Pink)
                LiveCard("🧑🏻", "Gaming Live", "RS Gaming", "6.3K", Cyan)
                LiveCard("👩🏽", "Chat & Chill", "Samiya Khan", "5.1K", Purple)
                LiveCard("🧔🏻", "Let's Talk", "Arif Hasan", "4.2K", Pink)
            }

            SectionTitle("⭐ Popular Hosts") { navigate("Discover") }
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
                    .padding(horizontal = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                HostItem("👩🏻", "Riya Queen", "1.2M")
                HostItem("🧑🏻", "RS Gaming", "980K")
                HostItem("👩🏽", "Maya Live", "870K")
                HostItem("👩🏻", "Samiya", "760K")
                HostItem("🧔🏻", "Arif Hasan", "690K")
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF5000A8), Color(0xFF160D52))
                        ), RoundedCornerShape(18.dp)
                    )
                    .border(1.dp, Purple, RoundedCornerShape(18.dp))
                    .clickable { navigate("Gifts") }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🎁", fontSize = 30.sp)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Send Gifts & Support", color = White,
                        fontWeight = FontWeight.Bold)
                    Text("Your favorite host ❤️", color = Muted, fontSize = 12.sp)
                }
                Spacer(Modifier.weight(1f))
                Text("›", color = Pink, fontSize = 30.sp)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Color(0xFF09091F))
                .border(1.dp, Color(0xFF33205A))
                .navigationBarsPadding()
                .padding(vertical = 9.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem("⌂", "Home", page, navigate)
            NavItem("◈", "Discover", page, navigate)
            NavItem("●", "Go Live", page, navigate, true)
            NavItem("✉", "Messages", page, navigate)
            NavItem("♙", "Profile", page, navigate)
        }
    }
}

@Composable
fun QuickTile(emoji: String, label: String) {
    Column(
        modifier = Modifier.width(100.dp)
            .background(Panel, RoundedCornerShape(16.dp))
            .border(1.dp, Purple, RoundedCornerShape(16.dp))
            .padding(vertical = 13.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(emoji, fontSize = 25.sp)
        Text(label, color = White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SectionTitle(title: String, onSeeAll: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 17.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, color = White, fontSize = 20.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        Text("See All  ›", color = Pink, fontSize = 13.sp,
            modifier = Modifier.clickable { onSeeAll() })
    }
}

@Composable
fun LiveCard(
    emoji: String, title: String, host: String,
    viewers: String, accent: Color
) {
    Column(
        modifier = Modifier.width(155.dp)
            .background(Panel, RoundedCornerShape(17.dp))
            .border(1.dp, accent, RoundedCornerShape(17.dp))
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(105.dp)
                .background(
                    Brush.linearGradient(
                        listOf(accent.copy(alpha = 0.7f), Color(0xFF15152F))
                    ), RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 54.sp)
            Text("● LIVE", color = White, fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopStart)
                    .background(Pink, RoundedCornerShape(12.dp))
                    .padding(horizontal = 7.dp, vertical = 4.dp))
            Text("◉ $viewers", color = White, fontSize = 10.sp,
                modifier = Modifier.align(Alignment.TopEnd))
        }
        Spacer(Modifier.height(8.dp))
        Text(title, color = White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        Text(host, color = Muted, fontSize = 11.sp)
    }
}

@Composable
fun HostItem(emoji: String, name: String, fans: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(68.dp)
                .background(Brush.linearGradient(listOf(Pink, Purple, Cyan)), CircleShape)
                .padding(3.dp).background(Panel, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 34.sp)
        }
        Spacer(Modifier.height(5.dp))
        Text(name, color = White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text("$fans Fans", color = Muted, fontSize = 10.sp)
    }
}

@Composable
fun NavItem(
    symbol: String, label: String, selected: String,
    navigate: (String) -> Unit, special: Boolean = false
) {
    val active = selected == label
    Column(
        modifier = Modifier.clickable { navigate(label) }.padding(horizontal = 7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(symbol, color = if (active || special) Pink else Muted,
            fontSize = if (special) 30.sp else 24.sp, fontWeight = FontWeight.Bold)
        Text(label, color = if (active || special) Pink else Muted,
            fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SimplePage(title: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Bg).padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("‹", color = Cyan, fontSize = 30.sp,
                modifier = Modifier.clickable { onBack() })
            Spacer(Modifier.width(15.dp))
            Text(title, color = White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(35.dp))
        Text("SRS $title", color = Cyan, fontSize = 22.sp)
        Spacer(Modifier.height(12.dp))
        Text("This section will be developed next.",
            color = Muted, fontSize = 14.sp)
        Spacer(Modifier.height(25.dp))
        Button(onClick = onBack) { Text("Back to Home") }
    }
}

@Composable
fun AccountScreen(title: String, onBack: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(Bg)
            .verticalScroll(rememberScrollState()).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(35.dp))
        Text("SRS LIVESTREAM", color = Cyan, fontSize = 27.sp,
            fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(25.dp))
        Text(title, color = White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(25.dp))

        if (title == "Create Account") {
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
        }

        OutlinedTextField(
            value = phone, onValueChange = { phone = it },
            label = { Text("Phone Number") }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("Password") }, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(22.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Pink)
        ) {
            Text(if (title == "Login") "LOGIN" else "SIGN UP")
        }
        Spacer(Modifier.height(20.dp))
        TextButton(onClick = onBack) {
            Text("Back", color = Cyan)
        }
    }
}
