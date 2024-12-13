package com.doaayahibu.venector

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.doaayahibu.venector.ui.theme.VenectorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val context = LocalContext.current

    Scaffold(
        topBar = { MediumTopAppBar(context) },
        bottomBar = { BottomNavigationBar(context) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Welcome Message
                    item {
                        HeaderSection()
                    }

                    // Quick Actions
                    item {
                        QuickActionsRow(context)
                    }

                    // Mentor Banner
                    item {
                        MentorBanner()
                    }

                    // Kategori Kebutuhan
                    item {
                        KategoriKebutuhan()
                    }

                    // Status Transaksi
                    item {
                        StatusTransaksi()
                    }

                    // Produk Terbaik Bulan Ini
                    item {
                        ProdukTerbaikBulanIni()
                    }
                }
            }
        }
    )
}

@Composable
fun HeaderSection() {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = "Selamat datang, Doa Ayah Ibu",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Sudah kaya kah kamu hari ini?",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun QuickActionsRow(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QuickActionItem(context, R.drawable.cart, "VenPay TopUp")
        QuickActionItem(context, R.drawable.cart, "Transfer Bank")
        QuickActionItem(context, R.drawable.cart, "Scan QRIS")
        QuickActionItem(context, R.drawable.cart    , "Tarik Tunai")
    }
}

@Composable
fun MentorBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Perbaikan elevasi
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFF1A4870)) // Latar belakang biru
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Bagian Kiri: Gambar Mentor
            Image(
                painter = painterResource(id = R.drawable.me7),
                contentDescription = "Mentor Image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape) // Bentuk lingkaran
                    .border(2.dp, Color.White, CircleShape), // Border putih
                contentScale = ContentScale.Crop // Memastikan gambar memenuhi lingkaran
            )

            // Bagian Tengah: Deskripsi
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "Mentor With Haga",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = Color.White
                )
                Text(
                    text = "Hanya 30K ajah",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f) // Teks lebih lembut
                )
            }

            // Bagian Kanan: Tombol
            Button(
                onClick = { /* Handle Booking */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF9DBBA) // Warna latar belakang tombol
                ),
                shape = RoundedCornerShape(16.dp), // Bentuk tombol rounded sesuai desain
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = "Book Here",
                    color = Color.Black, // Warna teks
                    fontWeight = FontWeight.Bold // Menonjolkan teks
                )
            }
        }
    }
}


@Composable
fun KategoriKebutuhan() {
    Column {
        Text(
            text = "Kamu lagi butuh apa nih?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OptionItem("Mentoring", R.drawable.chat)
            OptionItem("Financing", R.drawable.home)
            OptionItem("Collaboration", R.drawable.home)
        }
    }
}

@Composable
fun StatusTransaksi() {
    Column {
        Text(
            text = "Status Transaksi",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        CardListItem("Nama Produk 1")
        CardListItem("Nama Produk 2")
    }
}

@Composable
fun ProdukTerbaikBulanIni() {
    Column {
        Text(
            text = "Produk terbaik Bulan ini",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProductCard("Tas Branded", R.drawable.cart)
            ProductCard("Jaket Fashion", R.drawable.cart)
        }
    }
}

@Composable
fun BottomNavigationBar(context: Context) {
    BottomAppBar(
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = { /* Navigate Home */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "Home"
                    )
                }
                IconButton(onClick = { /* Navigate Cart */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "Cart"
                    )
                }
                IconButton(onClick = { /* Navigate Chat */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "Chat"
                    )
                }
                IconButton(onClick = { /* Navigate Profile */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart  ),
                        contentDescription = "Profile"
                    )
                }
            }
        },
        containerColor = Color.White,
        contentColor = Color(0xFF5B99C2)
    )
}

@Composable
fun QuickActionItem(context: Context, icon: Int, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { showToast(context, "$title Clicked") }) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF5B99C2)
            )
        }
        Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBar(context: Context) {
    TopAppBar(
        title = {
            Text(
                text = "Venector",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF5B99C2)
        ),
        actions = {
            IconButton(onClick = { showToast(context, "Notifikasi Klik!") }) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Notifikasi",
                    tint = Color.White
                )
            }
        }
    )
}


@Composable
fun OptionItem(title: String, iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(Color(0xFF5B99C2), shape = CircleShape)
                .clip(CircleShape)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.align(Alignment.Center),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@Composable
fun CardListItem(title: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Perbaikan elevasi
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cart), // Placeholder untuk ikon
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF5B99C2)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}


@Composable
fun ProductCard(title: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Perbaikan elevasi
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}


fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VenectorTheme {
        HomePage()
        }
}