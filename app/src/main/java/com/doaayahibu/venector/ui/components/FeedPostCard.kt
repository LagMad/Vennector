package com.doaayahibu.venector.ui.components

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.doaayahibu.venector.EditPostScreenActivity
import com.doaayahibu.venector.R
import com.doaayahibu.venector.showToast
import com.google.firebase.database.FirebaseDatabase

@Composable
fun PostCard(
    name: String = "Unknown",
    time: Long = 0L,
    description: String = "",
    imageUrls: List<String> = emptyList(),
    views: Long = 0L,
    likes: Long = 0L,
    comments: Long = 0L,
    postId: String,
    isLikedDb: Boolean
) {
    val context = LocalContext.current
    var isFavorited by remember { mutableStateOf(isLikedDb) }
    var localLikes by remember { mutableStateOf(likes) }

    val db = FirebaseDatabase.getInstance()
    val postRef = db.getReference("posts/$postId")

    fun updateLikes(isFavorited: Boolean) {
        val newLikes = if (isFavorited) localLikes + 1 else localLikes - 1

        Log.d("PostCard", "Post ID: $postId, isFavorited: $isFavorited, newLikes: $newLikes")

        postRef.child("likes").setValue(newLikes)
            .addOnSuccessListener {
                localLikes = newLikes
                showToast(context, "Like count update!")
            }
            .addOnFailureListener { exception ->
                showToast(context, "Failed to update like count: ${exception.message}")
            }
    }

    // Function to calculate hours ago
    fun getTimeAgo(postTime: Long): String {
        val currentTime = System.currentTimeMillis()
        val diffInMillis = currentTime - postTime
        val hoursAgo = (diffInMillis / (1000 * 60 * 60))  // Convert milliseconds to hours
        return "$hoursAgo jam yang lalu"
    }

    // Get the formatted time
    val formattedTime = getTimeAgo(time)

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

                    Text(text = formattedTime, fontWeight = FontWeight.Thin, color = Color.Black)
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
                            .placeholder(R.drawable.profile)  // Ensure you have a valid placeholder
                            .error(R.drawable.ic_launcher_foreground) // Use a fallback error image
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .height(250.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )

                }
            }

            Button(
                onClick = { showToast(context, "Financing!") },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B99C2)
                )
            ) {
                Text(text = "FINANCE", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
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
                        isFavorited = !isFavorited
                        updateLikes(isFavorited)
                    }) {
                        Icon(
                            imageVector = if (isFavorited) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Love Icon",
                            tint = if (isFavorited) Color.Red else Color.Black
                        )
                    }

                    Text(text = "$localLikes", color = Color.Black) // Display local like count
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = {
                        // Trigger Edit Action (Navigate to Edit Post Screen)
                        context.startActivity(Intent(context, EditPostScreenActivity::class.java).apply {
                            putExtra("POST_ID", postId)
                        })
                    }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit Post", tint = Color.Black)
                    }
                    IconButton(onClick = {
                        // Confirm before deleting
                        showDeleteConfirmationDialog(context, db, postId)
                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete Post", tint = Color.Black)
                    }
                }
            }
        }
    }
}

fun showDeleteConfirmationDialog(context: Context, database: FirebaseDatabase, postId: String) {
    // Display a confirmation dialog to delete the post
    AlertDialog.Builder(context)
        .setTitle("Delete Post")
        .setMessage("Are you sure you want to delete this post?")
        .setPositiveButton("Yes") { _, _ ->
            // Delete the post from Firebase using postRef
            database.getReference("posts").child(postId).removeValue()
                .addOnSuccessListener {
                    showToast(context, "Post deleted successfully")
                }
                .addOnFailureListener { error ->
                    showToast(context, "Failed to delete post: ${error.message}")
                }
        }
        .setNegativeButton("No", null)
        .show()
}
