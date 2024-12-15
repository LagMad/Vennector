package com.doaayahibu.venector
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Profit()
        }
    }
}

@Composable
fun Profit  () {
    var years by remember { mutableStateOf(3) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4CAF50))
            .padding(16.dp)
    ) {
        // Header Section
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* Back Action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Column {
                Text(
                    text = "Bakso Investment",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Rp 9.000.000",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Table Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Year", fontWeight = FontWeight.Bold)
            Text(text = "Net Profit", fontWeight = FontWeight.Bold)
            Text(text = "ROI", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Table Content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            val data = listOf(
                Triple(1, "Rp 0", "0%"),
                Triple(2, "Rp 0", "0%"),
                Triple(3, "Rp 0", "0%"),
                Triple(4, "Rp 0", "0%"),
                Triple(5, "Rp 4.050.000", "15%"),
                Triple(6, "Rp 5.400.000", "20%"),
                Triple(7, "Rp 4.050.000", "15%"),
                Triple(8, "Rp 5.400.000", "20%"),
                Triple(9, "Rp 4.050.000", "15%"),
                Triple(10, "Rp 5.400.000", "20%"),
                Triple(11, "Rp 4.050.000", "15%"),
                Triple(12, "Rp 5.400.000", "20%")
            )

            data.forEach { (year, profit, roi) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (year % 2 == 0) Color(0xFFF5F5F5) else Color.White)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$year")
                    Text(text = profit)
                    Text(text = roi)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total and IRR
        Text(
            text = "Total Rp 158.572.800",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "IRR 17.70%",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Years Selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { if (years > 0) years-- }) {
                Text(text = "−")
            }
            Text(
                text = "$years",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 16.sp,
                color = Color.White
            )
            Button(onClick = { years++ }) {
                Text(text = "+")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Calculate Button
        Button(
            onClick = { /* Calculate Action */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)) // Perbaikan di sini
        ) {
            Text(text = "Calculate", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBaksoInvestmentScreen() {
    Profit()
}
