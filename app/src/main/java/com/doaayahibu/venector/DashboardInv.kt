package com.doaayahibu.venector

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder



import com.doaayahibu.venector.ui.theme.VenectorTheme

val customGreen = Color(0xFF4DC591) // Custom green color

class InvestorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardInv()
        }
    }
}

@Composable
fun DashboardInv() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar Section
        TopBar()

        // Title: Your Schedule
        Text(
            text = "Your Schedule",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        // Schedule Section
        ScheduleCard(
            title = "Online Investment Meeting",
            date = "Wed, 24 Feb 2024",
            time = "2:00 PM",
            link = "Link Zoom",
            umkm = "UMKM Doa Ayah Ibu"
        )

        // Title: Feeds Section
        FeedsTitle()

        // Feeds Section
        FeedList()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
//    SmallTopAppBar(
//        title = {
//            Text(
//                "Selamat datang, Investor",
//                style = MaterialTheme.typography.titleMedium
//            )
//        },
//        actions = {
//            IconButton(onClick = { /* Handle Profile */ }) {
//                Icon(Icons.Filled.Person, contentDescription = "Profile")
//            }
//        },
//        colors = TopAppBarDefaults.smallTopAppBarColors(
//            containerColor = customGreen // Custom color
//        )
//    )
}

@Composable
fun ScheduleCard(title: String, date: String, time: String, link: String, umkm: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(100.dp),
        colors = CardDefaults.cardColors(containerColor = customGreen)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, color = Color.White)
            Spacer(modifier = Modifier.height(4.dp))
            Text("$date • $time", style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.7f))
            Text(link, style = MaterialTheme.typography.bodySmall.copy(color = Color.Yellow))
            Spacer(modifier = Modifier.height(8.dp))
            Text("UMKM: $umkm", style = MaterialTheme.typography.bodyMedium, color = Color.White)
        }
    }
}

@Composable
fun FeedItem(
    profileImage: Int,
    userName: String,
    timePosted: String,
    description: String,
    photo: Int,
    views: Int,
    likes: Int,
    comments: Int,
    backgroundColor: Color,
    onInvestClicked: () -> Unit
) {
    var isLiked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = profileImage),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(userName, style = MaterialTheme.typography.bodyMedium)
                    Text(
                        timePosted,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(description, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))

            // Post Image
            Image(
                painter = painterResource(id = photo),
                contentDescription = "Post Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Footer
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("$views views", style = MaterialTheme.typography.labelSmall)
                Text("$likes likes", style = MaterialTheme.typography.labelSmall)
                Text("$comments comments", style = MaterialTheme.typography.labelSmall)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Invest Button
            Button(
                onClick = onInvestClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = customGreen)
            ) {
                Text("Invest!", style = MaterialTheme.typography.labelMedium.copy(color = Color.White))
            }
        }
    }
}

@Composable
fun FeedList() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(5) { index ->
            val backgroundColor = if (index % 2 == 0) Color(0xFFD6E5EE) else Color(0xFFEEEBD6)
            FeedItem(
                profileImage = R.drawable.ic_launcher_foreground,
                userName = "UMKM $index",
                timePosted = "${index + 1} hours ago",
                description = "Deskripsi UMKM ke-$index",
                photo = R.drawable.ic_launcher_foreground,
                views = 100 * (index + 1),
                likes = 50 * (index + 1),
                comments = 20 * (index + 1),
                backgroundColor = backgroundColor,
                onInvestClicked = { /* Handle Invest */ }
            )
        }
    }
}

@Composable
fun FeedsTitle() {
    var selectedTab by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Feeds", style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                TextButton(
                    onClick = { selectedTab = 0 },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (selectedTab == 0) customGreen else Color.Transparent
                    )
                ) {
                    Text("Recent", color = if (selectedTab == 0) Color.White else customGreen)
                }
                TextButton(
                    onClick = { selectedTab = 1 },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = if (selectedTab == 1) customGreen else Color.Transparent
                    )
                ) {
                    Text("Popular", color = if (selectedTab == 1) Color.White else customGreen)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VenectorTheme {
        DashboardInv()
    }
}
