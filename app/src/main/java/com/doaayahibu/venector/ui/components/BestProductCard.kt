package com.doaayahibu.venector.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.RemoveRedEye
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.Feeds
import com.doaayahibu.venector.R
import com.doaayahibu.venector.showToast

@Composable
fun BestProductCard(title: String, imageRes: Int, modifier: Modifier = Modifier) {

    var isFavorited by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        modifier = modifier
//            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                // Background image from drawable
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .align(alignment = Alignment.Center)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 7.dp, y = -7.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(onClick = {
                        showToast(context, "Love Icon clicked")
                        isFavorited = !isFavorited
                    }) {
                        Icon(
                            imageVector = if (isFavorited) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Action Icon",
                            tint = if (isFavorited) Color.Red else Color.Black,
                            modifier = Modifier
                                .background(Color.White, shape = CircleShape)
                                .padding(7.dp)
                                .size(20.dp)
                        )
                    }
                    IconButton(onClick = {
                        showToast(context, "Love Icon clicked")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.RemoveRedEye,
                            contentDescription = "Action Icon",
                            tint = Color.Black,
                            modifier = Modifier
                                .background(Color.White, shape = CircleShape)
                                .padding(7.dp)
                                .size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
            )
        }
    }
}