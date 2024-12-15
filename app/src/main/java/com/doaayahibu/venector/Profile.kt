package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.ui.theme.VenectorTheme

class Profile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VenectorTheme {
                ProfilePage()
            }
        }
    }
}

@Composable
fun ProfilePage() {
    val context = LocalContext.current
    val images = listOf(
        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground
    )

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                            ) {
                                IconButton(onClick = {
                                    showToast(context, "Navigation Icon clicked")
                                    context.startActivity(Intent(context, Feeds::class.java))
                                }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Navigation Icon", tint = Color.Black)
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(190.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.profile),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(120.dp)
                                        .align(Alignment.BottomCenter)
                                        .background(Color.White, shape = CircleShape)
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth()
                                ,
                                verticalArrangement = Arrangement.spacedBy(0.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Doa Ayah Ibu", color = Color(0xFF242760), fontWeight = FontWeight.Black, fontSize = 24.sp)
                                Text(text = "UMKM Makanan", color = Color.DarkGray, fontWeight = FontWeight.Normal, fontSize = 12.sp)
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Filled.LocationOn, contentDescription = "Location Icon", tint = Color.DarkGray)
                                    Text(text = "Malang, Indonesia", color = Color.DarkGray, fontWeight = FontWeight.Normal, fontSize = 12.sp)
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "122", color = Color(0xFF242760), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Text(text = "followers", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Normal)
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "67", color = Color(0xFF242760), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Text(text = "following", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Normal)
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "37k", color = Color(0xFF242760), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                                Text(text = "likes", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Normal)
                            }
                        }
                        
                        Button(
                            onClick = { showToast(context, "Edit Profile clicked") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF242760)
                            )
                        ) {
                            Text(text = "Edit Profile", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Photos", color = Color(0xFF242760), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                thickness = 1.dp,
                                color = Color.Gray
                            )

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                contentPadding = PaddingValues(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(images.size) { index ->
                                    Image(
                                        painter = painterResource(id = images[index]),
                                        contentDescription = "Image $index",
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth()
                                            .height(180.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.LightGray)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    VenectorTheme {
        ProfilePage()
    }
}