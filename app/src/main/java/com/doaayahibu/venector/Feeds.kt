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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

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
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = { MediumTopAppBarFeeds(context) },
        bottomBar = { BottomNavigationBar(context) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                Title()
                Posts()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBarFeeds(context: Context) {
    Column {
        TopAppBar(
            title = {
                Text(
                text = "Feeds",
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                color = Color.Black
            )},
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title() {
    val context = LocalContext.current

}

data class Post(
    val postId: String = "", // Add postId
    val title: String = "Unknown",
    val description: String = "No description available",
    val images: List<String> = emptyList(),
    val time: Long = 0,
    val views: Long = 0,
    val likes: Long = 0,
    val comments: Long = 0,
    val isLikedDb: Boolean = false
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Posts() {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    val postsList = remember { mutableStateOf<List<Post>>(emptyList()) }
    val filteredPosts = remember { mutableStateOf<List<Post>>(emptyList()) }

    // Function to fetch posts (you can put this in your ViewModel if using one)
    fun fetchPosts() {
        val database = FirebaseDatabase.getInstance().reference
        database.child("posts").get()
            .addOnSuccessListener { dataSnapshot ->
                val fetchedPosts = dataSnapshot.children.mapNotNull { snapshot ->
                    val post = snapshot.getValue(Post::class.java)
                    post?.copy(postId = snapshot.key ?: "") // Add postId from Firebase snapshot key
                }
                postsList.value = fetchedPosts
                // Set filtered posts initially to all posts
                filteredPosts.value = fetchedPosts
            }
            .addOnFailureListener { error ->
                showToast(context, "Failed to fetch posts: ${error.message}")
            }
    }

    LaunchedEffect(Unit) {
        fetchPosts() // Fetch posts when the composable is first loaded
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search Icon", tint = Color.Black)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color(0xFF5B99C2),
                unfocusedIndicatorColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Submit Search Button
        Button(
            onClick = {
                // Perform the search and filter the posts based on the search query
                filteredPosts.value = postsList.value.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                            it.description.contains(searchQuery, ignoreCase = true)
                }
                if (filteredPosts.value.isEmpty()) {
                    showToast(context, "No posts found for: $searchQuery")
                } else {
                    showToast(context, "Found ${filteredPosts.value.size} post(s) for: $searchQuery")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B99C2))
        ) {
            Text("Search", color = Color.White)
        }
    }

    if (filteredPosts.value.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredPosts.value) { post ->
                PostCard(
                    name = post.title,
                    time = post.time,
                    description = post.description,
                    imageUrls = post.images,
                    views = post.views,
                    likes = post.likes,
                    comments = post.comments,
                    postId = post.postId,
                    isLikedDb = post.isLikedDb
                )
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