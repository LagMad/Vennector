package com.doaayahibu.venector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doaayahibu.venector.ui.theme.VenectorTheme

class NotificationDetailPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val name = intent.getStringExtra("NAME") ?: "Unknown"
            val goal = intent.getStringExtra("GOAL") ?: "No goal"
            val day = intent.getStringExtra("DAY") ?: "No day"
            val time = intent.getStringExtra("TIME") ?: "No time"

            NotificationDetail(name, goal, day, time)
        }
    }
}

@Composable
fun NotificationDetail(name: String, goal: String, day: String, time: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Notification for: $name")
        Text("Goal: $goal")
        Text("Day: $day")
        Text("Time: $time")
    }
}
