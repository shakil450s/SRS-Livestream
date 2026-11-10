package com.srs.livestream
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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
            { page = "Login" },
            { page = "Create Account" },
            { page = "Home" }
        )
        "Login" -> AccountScreen("Login", { page = "Welcome" }) {
            page = "Home"
        }
        "Create Account" -> AccountScreen(
            "Create Account", { page = "Welcome" }
        ) { page = "Home" }
        "Home" -> HomeScreen { page = it }
        "Discover" -> DiscoverScreen { page = it }
        "Go Live" -> GoLiveScreen { page = it }
        "Messages" -> MessagesScreen { page = it }
        "Profile" -> ProfileScreen { page = it }
        "Gifts" -> GiftsScreen { page = it }
        "Top Hosts" -> TopHostsScreen { page = it }
        "Trending" -> DiscoverScreen { page = it }
        "Recharge" -> RechargeScreen { page = it }
        else -> HomeScreen { page = it }
    }
}

@Composable
fun WelcomeScreen(
    onLogin: () -> Unit,
    onSignup: () -> Unit,
    onExplore: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF17002F), Bg, Color(0xFF090D30))
                )
            ).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("▶", color = Cyan, fontSize = 72.sp,
            fontWeight = FontWeight.Black)
        Text("SRS LIVESTREAM", color = White,
            fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Text("CONNECT • STREAM • SHARE",
            color = Cyan, fontSize = 12.sp)
        Spacer(Modifier.height(35.dp))
        Text("Your world. Your stage.", color = White,
            fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text("Meet people, share moments and enjoy live rooms.",
            color = Muted, fontSize = 14.sp)
        Spacer(Modifier.height(35.dp))
        Button(
            onClick = onLogin,
            modifier = Modifier.fillMaxWidth().height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple
            ),
            shape = RoundedCornerShape(16.dp)
        ) { Text("LOGIN", fontWeight = FontWeight.Bold) }
        Spacer(Modifier.height(13.dp))
        OutlinedButton(
            onClick = onSignup,
            modifier = Modifier.fillMaxWidth().height(54.dp),
            shape = RoundedCornerShape(16.dp)
        ) { Text("CREATE ACCOUNT", color = Cyan) }
        Spacer(Modifier.height(18.dp))
        TextButton(onClick = onExplore) {
            Text("Explore as Guest", color = Muted)
        }
    }
}

@Composable
fun AccountScreen(
    title: String,
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(Bg)
            .verticalScroll(rememberScrollState()).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(45.dp))
        Text("▶", color = Cyan, fontSize = 55.sp)
        Text("SRS LIVESTREAM", color = Cyan,
            fontSize = 26.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(30.dp))
        Text(title, color = White, fontSize = 28.sp,
            fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(25.dp))

        if (title == "Create Account") {
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
        }
        OutlinedTextField(
            value = phone, onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = password, onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(25.dp))
        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth().height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Pink)
        ) {
            Text(if (title == "Login") "LOGIN" else "SIGN UP")
        }
        TextButton(onClick = onBack) {
            Text("Back", color = Cyan)
        }
    }
}

@Composable
fun AppBackground(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Bg),
        content = content
    )
}

@Composable
fun AppHeader(title: String, action: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("▶", color = Cyan, fontSize = 27.sp)
        Spacer(Modifier.width(10.dp))
        Text(title, color = White, fontSize = 21.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.weight(1f))
        Text("●", color = Pink, fontSize = 20.sp,
            modifier = Modifier.clickable { action() })
    }
}

@Composable
fun BottomNav(page: String, navigate: (String) -> Unit) {
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

@Composable
fun NavItem(
    symbol: String,
    label: String,
    selected: String,
    navigate: (String) -> Unit,
    special: Boolean = false
) {
    val active = selected == label
    Column(
        modifier = Modifier.clickable { navigate(label) }
            .padding(horizontal = 7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(symbol, color = if (active || special) Pink else Muted,
            fontSize = if (special) 30.sp else 24.sp,
            fontWeight = FontWeight.Bold)
        Text(label, color = if (active || special) Pink else Muted,
            fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SectionTitle(title: String, onSeeAll: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 17.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, color = White, fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f))
        Text("See All ›", color = Pink,
            modifier = Modifier.clickable { onSeeAll() })
    }
}

@Composable
fun QuickTile(emoji: String, label: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.width(78.dp)
            .background(Panel, RoundedCornerShape(15.dp))
            .border(1.dp, Purple, RoundedCornerShape(15.dp))
            .clickable { onClick() }.padding(vertical = 13.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(emoji, fontSize = 25.sp)
        Text(label, color = White, fontSize = 11.sp,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun LiveCard(
    emoji: String, title: String, host: String,
    viewers: String, accent: Color = Purple
) {
    Column(
        modifier = Modifier.width(155.dp)
            .background(Panel, RoundedCornerShape(17.dp))
            .border(1.dp, accent, RoundedCornerShape(17.dp))
            .padding(9.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(105.dp)
                .background(
                    Brush.linearGradient(
                        listOf(accent, Color(0xFF15152F))
                    ), RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(emoji, fontSize = 48.sp)
            Text("● LIVE", color = White, fontSize = 10.sp,
                modifier = Modifier.align(Alignment.TopStart)
                    .background(Pink, RoundedCornerShape(8.dp))
                    .padding(5.dp))
            Text("◉ $viewers", color = White, fontSize = 10.sp,
                modifier = Modifier.align(Alignment.TopEnd))
        }
        Spacer(Modifier.height(8.dp))
        Text(title, color = White, fontWeight = FontWeight.Bold,
            fontSize = 13.sp)
        Text(host, color = Muted, fontSize = 11.sp)
    }
}

@Composable
fun HomeScreen(navigate: (String) -> Unit) {
    AppBackground {
        Column(
            modifier = Modifier.weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            AppHeader("SRS LIVESTREAM")

            Box(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
                    .height(165.dp)
                    .background(
                        Brush.linearGradient(listOf(Purple, Color(0xFF151044), Pink)),
                        RoundedCornerShape(20.dp)
                    ).clickable { navigate("Discover") }.padding(18.dp)
            ) {
                Column {
                    Text("● LIVE TOGETHER", color = White,
                        fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Text("Your stage.\nYour people.", color = White,
                        fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                    Spacer(Modifier.height(8.dp))
                    Text("JOIN NOW  →", color = Cyan,
                        fontWeight = FontWeight.Bold)
                }
                Text("🎧", fontSize = 65.sp,
                    modifier = Modifier.align(Alignment.CenterEnd))
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuickTile("🔥", "Live Rooms") { navigate("Discover") }
                QuickTile("⭐", "Top Hosts") { navigate("Top Hosts") }
                QuickTile("🎁", "Gifts") { navigate("Gifts") }
                QuickTile("👑", "Recharge") { navigate("Recharge") }
            }

            SectionTitle("Popular Live Rooms") {
                navigate("Discover")
            }
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LiveCard("🎤", "Music Night ❤️", "SRS Queen", "12.5K")
                LiveCard("🎮", "Fun & Chat", "RDX Hero", "8.7K", Cyan)
                LiveCard("💃", "Dance Time", "Lovely Girl", "7.6K", Pink)
            }

            SectionTitle("Popular Hosts") { navigate("Top Hosts") }
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                HostItem("👩", "Sana", "248K")
                HostItem("👑", "Mim", "197K")
                HostItem("🎤", "Riya", "185K")
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(15.dp)
                    .background(Panel, RoundedCornerShape(17.dp))
                    .border(1.dp, Purple, RoundedCornerShape(17.dp))
                    .clickable { navigate("Gifts") }.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🎁", fontSize = 30.sp)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Send Gifts & Support", color = White,
                        fontWeight = FontWeight.Bold)
                    Text("Your favorite host ❤️", color = Muted,
                        fontSize = 12.sp)
                }
                Spacer(Modifier.weight(1f))
                Text("›", color = Pink, fontSize = 28.sp)
            }
        }
        BottomNav("Home", navigate)
    }
}

@Composable
fun HostItem(emoji: String, name: String, fans: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(65.dp)
                .background(
                    Brush.linearGradient(listOf(Pink, Purple, Cyan)),
                    CircleShape
                ).padding(3.dp).background(Panel, CircleShape),
            contentAlignment = Alignment.Center
        ) { Text(emoji, fontSize = 32.sp) }
        Text(name, color = White, fontSize = 12.sp,
            fontWeight = FontWeight.Bold)
        Text("$fans Fans", color = Muted, fontSize = 10.sp)
    }
}




@Composable
fun DiscoverScreen(navigate: (String) -> Unit) {
    var category by remember { mutableStateOf("All") }
    var search by remember { mutableStateOf("") }

    val categories = listOf("All", "Music", "Gaming", "Chat", "Dance")

    val hosts = listOf(
        Triple("🎤", "Music Vibes ❤️", "SRS Queen"),
        Triple("🎮", "Game Zone", "Gaming Boy"),
        Triple("💬", "Chat Room", "Riya"),
        Triple("💃", "Dance Time", "Mim")
    )

    val filteredHosts = hosts.filter { host ->
        val matchesCategory = category == "All" ||
            (category == "Music" && host.second.contains("Music")) ||
            (category == "Gaming" && host.second.contains("Game")) ||
            (category == "Chat" && host.second.contains("Chat")) ||
            (category == "Dance" && host.second.contains("Dance"))

        val matchesSearch = search.isBlank() ||
            host.second.contains(search, ignoreCase = true) ||
            host.third.contains(search, ignoreCase = true)

        matchesCategory && matchesSearch
    }

    AppBackground {
        Column(modifier = Modifier.weight(1f)) {
            AppHeader("Discover")

            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                placeholder = {
                    Text("find live hosts...", color = Muted)
                },
                singleLine = true,
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Pink,
                    unfocusedBorderColor = Purple,
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                    cursorColor = Cyan
                )
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { item ->
                    FilterChip(
                        selected = category == item,
                        onClick = { category = item },
                        label = { Text(item) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Pink,
                            selectedLabelColor = White
                        )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(12.dp)
            ) {
                Text(
                    "🔴 LIVE NOW",
                    color = Pink,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                if (filteredHosts.isEmpty()) {
                    Text("No hosts found", color = White)
                } else {
                    filteredHosts.forEach { host ->
                        LiveCard(
                            host.first,
                            host.second,
                            host.third,
                            "Live",
                            Cyan
                        )
                        Spacer(Modifier.height(12.dp))
                    }
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    "⭐ RECOMMENDED HOSTS",
                    color = White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    HostItem("👩", "Sana", "248K")
                    HostItem("👑", "Mim", "197K")
                    HostItem("🎤", "Riya", "185K")
                }
            }
        }

        BottomNav("Discover", navigate)
    }
}


@Composable
fun GoLiveScreen(navigate: (String) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var cameraGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    var micGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            cameraGranted =
                permissions[Manifest.permission.CAMERA] == true
            micGranted =
                permissions[Manifest.permission.RECORD_AUDIO] == true
        }

    AppBackground {
        Column(
            modifier = Modifier.weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppHeader("Go Live")

            Spacer(Modifier.height(20.dp))

            if (cameraGranted) {
                AndroidView(
                    factory = { viewContext ->
                        PreviewView(viewContext).apply {
                            scaleType =
                                PreviewView.ScaleType.FILL_CENTER

                            val cameraProviderFuture =
                                ProcessCameraProvider.getInstance(viewContext)

                            cameraProviderFuture.addListener({
                                val cameraProvider =
                                    cameraProviderFuture.get()

                                val preview = Preview.Builder()
                                    .build()
                                    .also {
                                        it.setSurfaceProvider(
                                            surfaceProvider
                                        )
                                    }

                                try {
                                    cameraProvider.unbindAll()
                                    cameraProvider.bindToLifecycle(
                                        lifecycleOwner,
                                        CameraSelector.DEFAULT_FRONT_CAMERA,
                                        preview
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }, ContextCompat.getMainExecutor(viewContext))
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(300.dp)
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(300.dp)
                        .background(Panel, RoundedCornerShape(18.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("📷 Camera Preview", color = White,
                        fontSize = 24.sp)
                }
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Start Your Live",
                color = White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Share your talent with the world",
                color = Muted,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(20.dp))

            ActionRow(
                if (cameraGranted) "✅" else "📷",
                if (cameraGranted) "Camera Permission Granted"
                else "Camera Permission Needed"
            )

            ActionRow(
                if (micGranted) "✅" else "🎤",
                if (micGranted) "Microphone Permission Granted"
                else "Microphone Permission Needed"
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    if (!cameraGranted || !micGranted) {
                        permissionLauncher.launch(
                            arrayOf(
                                Manifest.permission.CAMERA,
                                Manifest.permission.RECORD_AUDIO
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Pink
                )
            ) {
                Text(
                    if (cameraGranted && micGranted)
                        "PERMISSIONS GRANTED"
                    else "ALLOW CAMERA & MICROPHONE",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(15.dp))

            Text(
                "Be Safe & Follow Community Rules ❤️",
                color = Muted,
                fontSize = 12.sp
            )
        }

        BottomNav("Go Live", navigate)
    }
}



@Composable
fun ActionRow(icon: String, title: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
            .background(Panel, RoundedCornerShape(14.dp))
            .border(1.dp, Purple, RoundedCornerShape(14.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icon, color = Cyan, fontSize = 22.sp)
        Spacer(Modifier.width(15.dp))
        Text(title, color = White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun MessagesScreen(navigate: (String) -> Unit) {
    AppBackground {
        Column(modifier = Modifier.weight(1f)) {
            AppHeader("Messages")
            Column(
                modifier = Modifier.weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(15.dp)
            ) {
                MessageItem("👩", "Riya Queen", "Hey! 😊", "9:30 PM")
                MessageItem("🎮", "Gaming Boy", "Nice stream bro!", "8:45 PM")
                MessageItem("💗", "SRS Fan Club", "Welcome to SRS! ❤️", "7:20 PM")
                MessageItem("🌸", "Lovely Girl", "Thank you 😊", "6:50 PM")
            }
        }
        BottomNav("Messages", navigate)
    }
}

@Composable
fun MessageItem(emoji: String, name: String, message: String, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 9.dp)
            .background(Panel, RoundedCornerShape(15.dp)).padding(13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(emoji, fontSize = 35.sp)
        Spacer(Modifier.width(13.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(name, color = White, fontWeight = FontWeight.Bold)
            Text(message, color = Muted, fontSize = 12.sp)
        }
        Text(time, color = Cyan, fontSize = 10.sp)
    }
}

@Composable
fun ProfileScreen(navigate: (String) -> Unit) {
    AppBackground {
        Column(
            modifier = Modifier.weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppHeader("Profile")
            Spacer(Modifier.height(15.dp))
            Box(
                modifier = Modifier.size(100.dp)
                    .background(
                        Brush.linearGradient(listOf(Pink, Purple, Cyan)),
                        CircleShape
                    ).padding(4.dp).background(Panel, CircleShape),
                contentAlignment = Alignment.Center
            ) { Text("👑", fontSize = 45.sp) }
            Spacer(Modifier.height(12.dp))
            Text("SRS Lover", color = White, fontSize = 23.sp,
                fontWeight = FontWeight.Bold)
            Text("@srsl over", color = Cyan, fontSize = 12.sp)
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("125", "Following")
                StatItem("12.5K", "Fans")
                StatItem("2.4K", "Likes")
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Purple)
            ) { Text("Edit Profile") }
            Spacer(Modifier.height(15.dp))
            listOf(
                "🎁  My Gifts",
                "💎  My Wallet",
                "⚙️  Settings",
                "❔  Help & Support",
                "ℹ️  About SRS"
            ).forEach {
                ActionRow("›", it)
            }
        }
        BottomNav("Profile", navigate)
    }
}

@Composable
fun StatItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(number, color = White, fontSize = 19.sp,
            fontWeight = FontWeight.Bold)
        Text(label, color = Muted, fontSize = 12.sp)
    }
}

@Composable
fun GiftsScreen(navigate: (String) -> Unit) {
    AppBackground {
        Column(modifier = Modifier.weight(1f)) {
            AppHeader("Gifts")
            Column(
                modifier = Modifier.weight(1f)
                    .verticalScroll(rememberScrollState()).padding(15.dp)
            ) {
                Text("Send Gifts", color = White, fontSize = 22.sp,
                    fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(15.dp))
                val gifts = listOf(
                    "🌹" to "Rose • 10",
                    "💗" to "Love • 50",
                    "⭐" to "Star • 100",
                    "👑" to "Crown • 500",
                    "💎" to "Diamond • 1000",
                    "🚀" to "Rocket • 2000"
                )
                gifts.chunked(2).forEach { pair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        pair.forEach { gift ->
                            Column(
                                modifier = Modifier.weight(1f).padding(5.dp)
                                    .background(Panel, RoundedCornerShape(16.dp))
                                    .border(1.dp, Purple, RoundedCornerShape(16.dp))
                                    .padding(18.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(gift.first, fontSize = 40.sp)
                                Text(gift.second, color = White,
                                    fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
        BottomNav("Gifts", navigate)
    }
}

@Composable
fun TopHostsScreen(navigate: (String) -> Unit) {
    AppBackground {
        Column(modifier = Modifier.weight(1f)) {
            AppHeader("Top Hosts")
            Column(
                modifier = Modifier.weight(1f)
                    .verticalScroll(rememberScrollState()).padding(15.dp)
            ) {
                HostItem("👑", "Sana", "248K")
                HostItem("🌸", "Mim", "197K")
                HostItem("🎤", "Riya", "185K")
                HostItem("🎮", "Gaming Boy", "120K")
            }
        }
        BottomNav("Discover", navigate)
    }
}

@Composable
fun TrendingScreen(navigate: (String) -> Unit) {
    DiscoverScreen(navigate)
}

@Composable
fun RechargeScreen(navigate: (String) -> Unit) {
    AppBackground {
        Column(
            modifier = Modifier.weight(1f).padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppHeader("Recharge")
            Text("💎", fontSize = 70.sp)
            Text("SRS Coins", color = White, fontSize = 25.sp,
                fontWeight = FontWeight.Bold)
            Text("Recharge options will be connected later.",
                color = Muted)
            Spacer(Modifier.height(20.dp))
            listOf("100 Coins", "500 Coins", "1000 Coins").forEach {
                ActionRow("💎", it)
            }
        }
        BottomNav("Home", navigate)
    }
}
