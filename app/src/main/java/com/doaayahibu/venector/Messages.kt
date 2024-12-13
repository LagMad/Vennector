package com.doaayahibu.venector

import android.content.Intent
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.ui.components.NavArrowBackTitle
import com.doaayahibu.venector.ui.components.NotifCard
import com.doaayahibu.venector.ui.components.TabButton
import com.doaayahibu.venector.ui.theme.VenectorTheme

class Messages : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VenectorTheme {
                MessagesPage()
            }
        }
    }
}

@Composable
fun MessagesPage() {
    val context = LocalContext.current

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
                    onClick = { context.startActivity(Intent(context, Feeds::class.java)) },
                    title = "Messages"
                )
                
                Row {
                    Spacer(modifier = Modifier.weight(0.5f))
                    TabButton(label = "Notification", modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.weight(0.1f))
                    TabButton(label = "Chat", modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.weight(0.5f))
                }

                Notifications()
            }
        }
    )
}

data class NotificationData(
    val name: String,
    val goal: String,
    val day: String,
    val time: String
)


@Composable
fun Notifications() {
    val notifications = listOf(
        NotificationData("Hago", "INVEST", "Monday", "9:42PM"),
        NotificationData("Aracel", "INVEST", "Friday", "1:00AM"),
        NotificationData("Jeremmy", "INVEST", "Yesterday", "8:52PM"),
        NotificationData("Joni", "BUY", "Today", "7:30AM"),
        NotificationData("Hago", "INVEST", "Monday", "9:42PM"),
        NotificationData("Aracel", "INVEST", "Friday", "1:00AM"),
        NotificationData("Jeremmy", "INVEST", "Yesterday", "8:52PM"),
        NotificationData("Joni", "BUY", "Today", "7:30AM"),
        NotificationData("Hago", "INVEST", "Monday", "9:42PM"),
        NotificationData("Aracel", "INVEST", "Friday", "1:00AM"),
        NotificationData("Jeremmy", "INVEST", "Yesterday", "8:52PM"),
        NotificationData("Joni", "BUY", "Today", "7:30AM"),
    )

    LazyColumn {
        itemsIndexed(notifications) { index, notification ->
            NotifCard(
                name = notification.name,
                goal = notification.goal,
                day = notification.day,
                time = notification.time
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    VenectorTheme {
        MessagesPage()
    }
}