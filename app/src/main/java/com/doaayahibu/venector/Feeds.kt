package com.doaayahibu.venector

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
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
//                    item {
//                        TitleButtons()
//                    }
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
                IconButton(onClick = { showToast(context, "Navigation Icon clicked") }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Navigation Icon")
                }
            },
            actions = {
                IconButton(onClick = { showToast(context, "Message Icon clicked") }) {
                    Icon(Icons.AutoMirrored.Filled.Message, contentDescription = "Message Icon")
                }
                IconButton(onClick = {
                    showToast(context, "Add Post Icon clicked")
                    context.startActivity(Intent(context, AddPost::class.java))
                }) {
                    Icon(Icons.Filled.AddCircleOutline, contentDescription = "Add Post Icon")
                }
                IconButton(onClick = { showToast(context, "Profile Icon clicked") }) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile Icon")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFFFFFFF),
                actionIconContentColor = Color.Black,
                navigationIconContentColor = Color.Black
            )
        )

        // Second Row: Title Buttons
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
            Button(
                onClick = {
                    showToast(context, "Recent button clicked")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 0.dp)
                    .padding(horizontal = 0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFFF6500)
                ),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = "Recent",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            Button(
                onClick = {
                    showToast(context, "Popular button clicked")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 1.dp)
                    .padding(horizontal = 0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFFF6500)
                ),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = "Popular",
                    modifier = Modifier
                        .padding(2.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun Posts() {
    // List of image URLs to be displayed
    val imageUrls = listOf(
        "https://uploads.dailydot.com/2018/10/olli-the-polite-cat.jpg?q=65&auto=format&w=1200&ar=2:1&fit=crop",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdBWB76EZKUgHdARYa-XNyIzoiJiUiyKiFrg&s",
        "https://uploads.dailydot.com/2024/07/side-eye-cat.jpg?q=65&auto=format&w=1600&ar=2:1&fit=crop"
    )

    PostCard(
        name = "Jeremmy",
        time = 2,
        description = "Wow kucingnya lucu sekaliii ingin saya cokot.",
        imageUrls = imageUrls,  // Pass the list of images
        views = 150,
        likes = 25,
        comments = 10
    )

    PostCard(
        name = "Hizkia",
        time = 5,
        description = "Meow meow meeooooowww meow meow purrr meow.",
        imageUrls = imageUrls,  // Pass the list of images
        views = 11240,
        likes = 1245,
        comments = 241
    )
}

@Composable
fun PostCard(
    name: String,
    time: Int,
    description: String,
    imageUrls: List<String>,
    views: Int,
    likes: Int,
    comments: Int
) {
    val context = LocalContext.current
    var isFavorited by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFD6E5EE)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row {
                IconButton(onClick = { val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/hizkiajeremmy"))
                    context.startActivity(intent) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        tint = Color.Black
                    )
                }

                Column {
                    Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)

                    Text(text = "$time jam yang lalu", fontWeight = FontWeight.Thin, color = Color.Black)
                }
            }

            Text(text = description, color = Color.Black)

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(imageUrls) { index, imageUrl ->
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(imageUrl)
                            .placeholder(R.drawable.loading)
//                            .error(R.drawable.error) // Error placeholder
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .height(250.dp)
                            .clip(RoundedCornerShape(16.dp))
                        ,
                        contentScale = ContentScale.Crop
                    )
                }
            }



            Row {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { showToast(context, "Message Icon clicked") }) {
                        Icon(Icons.Filled.RemoveRedEye, contentDescription = "Action Icon", tint = Color.Black)
                    }

                    Text(text = "$views", color = Color.Black )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        showToast(context, "Love Icon clicked")
                        isFavorited = !isFavorited
                    }) {
                        Icon(
                            imageVector = if (isFavorited) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Action Icon",
                            tint = if (isFavorited) Color.Red else Color.Black
                        )
                    }

                    Text(text = "$likes", color = Color.Black )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { showToast(context, "Message Icon clicked") }) {
                        Icon(Icons.AutoMirrored.Filled.Comment, contentDescription = "Action Icon", tint = Color.Black)
                    }

                    Text(text = "$comments", color = Color.Black )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedsPreview() {
    VenectorTheme {
        FeedsPage()
    }
}