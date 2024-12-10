package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.ui.components.NavArrowBackTitle
import com.doaayahibu.venector.ui.theme.VenectorTheme


class InvestorDescription : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VenectorTheme {
                InvestDescPage()
            }
        }
    }
}

@Composable
fun FloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = Color(0xff1A4870),
        contentColor = Color.White,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Set a Schedule!", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}


@Composable
fun InvestDescPage() {
    val context = LocalContext.current
    var isFavorited by remember { mutableStateOf(false) }

    Scaffold (
        floatingActionButton = {
            FloatingButton(onClick = { showToast(context, "Terjadwalkan beybsss") })
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NavArrowBackTitle(onClick = { context.startActivity(Intent(context, Messages::class.java)) }, title = "Investor Description")

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    item() {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(17.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .clip(RoundedCornerShape(20.dp))
                            ) {
                                // Background image from drawable
                                Image(
                                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(350.dp)
                                        .background(Color.Gray)
                                )

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
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
                                                .size(30.dp)
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
                                                .size(30.dp)
                                        )
                                    }
                                }
                            }

                            Text(text = "Haga Surbakti", color = Color.Black, fontSize = 36.sp, fontWeight = FontWeight.Black)

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(Icons.Filled.Star, contentDescription = "Star Icon", tint = Color(0xFFFFAD33))
                                Text(text = "4.6/5 (69)")
                            }

                            Text(
                                text = "Haga memulai Venector dari nol dengan keterbatasan modal dan pengalaman bisnis, namun berhasil membangun salah satu marketplace terbesar di Indonesia Ia memiliki wawasan tentang pengembangan bisnis rintisan dengan strategi digital. Haga memulai Venector dari nol dengan keterbatasan modal dan pengalaman bisnis, namun berhasil membangun salah satu marketplace terbesar di Indonesia Ia memiliki wawasan tentang pengembangan bisnis rintisan dengan strategi digital.",
                                color = Color.DarkGray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Justify
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .border(
                                            1.dp,
                                            Color.LightGray,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(16.dp)
                                ) {
                                    Text(text = "Expertise", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Normal)
                                    Text(
                                        modifier = Modifier
                                            .padding(3.dp) // Padding around the text
                                            .background(
                                                color = Color(0xFFFFF8F1), // Background color
                                                shape = RoundedCornerShape(8.dp) // Optional: Apply rounded corners
                                            )
                                            .padding(horizontal = 8.dp, vertical = 4.dp),
                                        text = "Leadership",
                                        color = Color(0xFFDA5B14),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                    Text(
                                        modifier = Modifier
                                            .padding(3.dp) // Padding around the text
                                            .background(
                                                color = Color(0xFFE8FFF8), // Background color
                                                shape = RoundedCornerShape(8.dp) // Optional: Apply rounded corners
                                            )
                                            .padding(horizontal = 6.dp, vertical = 4.dp),
                                        text = "Creative Direction",
                                        color = Color(0xFF0C8C5E),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .border(
                                            1.dp,
                                            Color.LightGray,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(16.dp)
                                ) {
                                    Text(text = "Reviews (10)", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Normal)
                                    Text(
                                        text = "Mentoring dengan William benar-benar membuka wawasan kami tentang pentingnya strategi digital untuk UMKM.",
                                        color = Color.DarkGray,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(70.dp))
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    VenectorTheme {
        InvestDescPage()
    }
}