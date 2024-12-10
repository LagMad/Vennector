package com.example.scheduleapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScheduleApp()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScheduleApp() {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = {
            // Pastikan ScheduleScreen memiliki padding yang benar
            ScheduleScreen(modifier = Modifier.padding(it))
        }
    )
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle back action */ }) {
            Icon(
                painter = painterResource(id = R.drawable.cart), // Ganti dengan ikon back
                contentDescription = "Back",
                tint = Color.Black
            )
        }
        Text(
            text = "Schedule",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ScheduleScreen(modifier: Modifier = Modifier) {
    // Pastikan ini memiliki padding yang benar
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        // Menampilkan Header Tanggal
        DateHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan List Jadwal
        LazyColumn {
            items(5) { // Menampilkan 5 item contoh
                ScheduleItem(
                    time = "2:00 PM",
                    topic = "Online Investment Meeting",
                    location = "Link Zoom\nUMKM Doa Ayah Ibu"
                )
            }
        }
    }
}

@Composable
fun DateHeader() {
    // Tanggal dan Hari saat ini
    val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    val currentDate = Date()

    // Menampilkan tanggal di kiri
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Today: ${dayFormat.format(currentDate)}, ${dateFormat.format(currentDate)}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan Hari dan Tanggal
        val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        val dates = listOf("21", "22", "23", "24", "25", "26", "27")

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                daysOfWeek.forEachIndexed { index, day ->
                    Text(
                        text = day,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Menampilkan Tanggal dalam bentuk Lingkaran
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                dates.forEachIndexed { index, date ->
                    DateCircle(date = date, isSelected = date == "24")
                }
            }
        }
    }
}

@Composable
fun DateCircle(date: String, isSelected: Boolean) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                if (isSelected) Color(0xFFFF5722) else Color.Transparent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Composable
fun ScheduleItem(time: String, topic: String, location: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = time,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = topic,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                IconButton(onClick = { /* Handle more options */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart), // Ganti dengan ikon lainnya
                        contentDescription = "More",
                        tint = Color.White
                    )
                }
            }
            Text(
                text = location,
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScheduleApp() {
    ScheduleApp()
}
