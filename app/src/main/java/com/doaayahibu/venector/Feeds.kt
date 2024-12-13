package com.doaayahibu.venector

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.ui.components.PostCard
import com.doaayahibu.venector.ui.components.TabButton
import com.doaayahibu.venector.ui.theme.VenectorTheme

class Feeds : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VenectorTheme {
                FeedsPage()
            }
        }
    }
}

@Composable
fun FeedsPage() {
    val context = LocalContext.current

    Scaffold(
        topBar = { MediumTopAppBarFeeds(context = context ) },
        bottomBar = { BottomNavigationBar(context) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Posts()
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBarFeeds(context: Context) {
    Column {
        // First Row: Navigation Icon, Actions, and Empty Title
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = {
                    showToast(context, "Navigation Icon clicked")
                    context.startActivity(Intent(context, Home::class.java))
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Navigation Icon")
                }
            },
            actions = {
                IconButton(onClick = {
                    showToast(context, "Message Icon clicked")
                    context.startActivity(Intent(context, Messages::class.java))
                }) {
                    Icon(Icons.AutoMirrored.Filled.Message, contentDescription = "Message Icon")
                }
                IconButton(onClick = {
                    showToast(context, "Add Post Icon clicked")
                    context.startActivity(Intent(context, AddPost::class.java))
                }) {
                    Icon(Icons.Filled.AddCircleOutline, contentDescription = "Add Post Icon")
                }
                IconButton(onClick = {
                    showToast(context, "Profile Icon clicked")
                    context.startActivity(Intent(context, Profile::class.java))
                }) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile Icon")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFFFFFFF),
                actionIconContentColor = Color.Black,
                navigationIconContentColor = Color.Black
            )
        )

        TitleButtons()
    }
}

@Composable
fun TitleButtons() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Feeds",
            fontSize = 36.sp,
            fontWeight = FontWeight.Black,
            color = Color.Black
        )

        Row(
            modifier = Modifier
                .width(200.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabButton(
                label = "Recent",
                modifier = Modifier.weight(1f)
            )
            TabButton(
                label = "Popular",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Posts() {

    val imageUrls = listOf(
        "https://uploads.dailydot.com/2018/10/olli-the-polite-cat.jpg?q=65&auto=format&w=1200&ar=2:1&fit=crop",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdBWB76EZKUgHdARYa-XNyIzoiJiUiyKiFrg&s",
        "https://uploads.dailydot.com/2024/07/side-eye-cat.jpg?q=65&auto=format&w=1600&ar=2:1&fit=crop"
    )

    PostCard(
        name = "Jeremmy",
        time = 2,
        description = "Wow kucingnya lucu sekaliii ingin saya cokot.",
        imageUrls = imageUrls,
        views = 150,
        likes = 25,
        comments = 10
    )

    PostCard(
        name = "Hizkia",
        time = 5,
        description = "Meow meow meeooooowww meow meow purrr meow.",
        imageUrls = imageUrls,
        views = 11240,
        likes = 1245,
        comments = 241
    )
}

@Preview(showBackground = true)
@Composable
fun FeedsPreview() {
    VenectorTheme {
        FeedsPage()
    }
}