package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.ui.components.NavArrowBackTitle
import com.doaayahibu.venector.ui.theme.VenectorTheme
import com.google.firebase.database.FirebaseDatabase


class EditPostScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val postId = intent.getStringExtra("POST_ID") ?: return // Ensure `POST_ID` is passed
        setContent {
            VenectorTheme {
                EditPostScreen(postId = postId) // Pass the postId here
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPostScreen(postId: String) {
    val context = LocalContext.current
    val database = FirebaseDatabase.getInstance().reference
    val post = remember { mutableStateOf<Post?>(null) }

    // Fetch the post data from Firebase
    LaunchedEffect(postId) {
        database.child("posts").child(postId).get().addOnSuccessListener { snapshot ->
            post.value = snapshot.getValue(Post::class.java)
        }
    }

    post.value?.let {
        var title by remember { mutableStateOf(it.title) }
        var description by remember { mutableStateOf(it.description) }
        var images by remember { mutableStateOf(it.images) }

        Scaffold(
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    NavArrowBackTitle(
                        onClick = {
                            showToast(context, "Navigation Icon clicked")
                            context.startActivity(Intent(context, Feeds::class.java))
                        },
                        title = "Edit Post"
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        TextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Title") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                unfocusedLabelColor = Color.DarkGray,
                                focusedLabelColor = Color.DarkGray,
                                focusedIndicatorColor = Color.Black,
                                cursorColor = Color.Black
                            )
                        )


                        // Description
                        TextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("Description") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                unfocusedLabelColor = Color.DarkGray,
                                focusedLabelColor = Color.DarkGray,
                                focusedIndicatorColor = Color.Black,
                                cursorColor = Color.Black
                            )
                        )

                        // Display and edit images (optional)
                        // Implement image URL editing logic here if needed

                        Spacer(modifier = Modifier.height(16.dp))

                        // Save Changes Button
                        Button(
                            onClick = {
                                val updatedPost = Post(
                                    postId = postId,
                                    title = title,
                                    description = description,
                                    images = images,
                                    time = it.time,
                                    views = it.views,
                                    likes = it.likes,
                                    comments = it.comments,
                                    isLikedDb = it.isLikedDb
                                )

                                // Update post in Firebase
                                database.child("posts").child(postId).setValue(updatedPost)
                                    .addOnSuccessListener {
                                        showToast(context, "Post updated successfully")
                                        context.startActivity(Intent(context, Feeds::class.java))
                                    }
                                    .addOnFailureListener { error ->
                                        showToast(context, "${error.message}")
                                    }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF1A4870)
                            )
                        ) {
                            Text(
                                text = "Save Changes",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        )
    } ?: run {
        // Show loading or error message if post data is not loaded
        CircularProgressIndicator()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview4() {
//    VenectorTheme {
//        EditPostScreen(postId = "sample_post_id") // Use a sample postId here for preview
//    }
//}
