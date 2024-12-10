package com.doaayahibu.venector.ui.components

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.doaayahibu.venector.R
import com.doaayahibu.venector.showToast

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
                            .placeholder(R.drawable.profile)
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