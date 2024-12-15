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
import androidx.compose.material3.IconButton
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
    )

    LazyColumn {
        itemsIndexed(notifications) { _, notification ->
            NotifCard(notification)
        }
    }
}

@Composable
fun NotifCard(notification: NotificationData) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            IconButton(onClick = { context.startActivity(Intent(context, InvestorDescription::class.java)) }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Profile Icon", modifier = Modifier.weight(1f), tint = Color(0xFF5B99C2))
            }

            Column(
                modifier = Modifier
                    .weight(8f)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                            append("${notification.name}")
                        }
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append(" wants to ") // Add the regular text before the bold part
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                            append("${notification.goal}") // Make goal bold and color it black
                        }
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append(" on your product!!") // Add the regular text after the bold part
                        }
                    }
                )


                Text(text = "What are you waiting for?", color = Color.Black)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "${notification.day} at ${notification.time}", color = Color.DarkGray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f),
                        onClick = {
                            // Navigate to another page and send the notification data
                            val intent = Intent(context, NotificationDetailPage::class.java).apply {
                                putExtra("NAME", notification.name)
                                putExtra("GOAL", notification.goal)
                                putExtra("DAY", notification.day)
                                putExtra("TIME", notification.time)
                            }
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5B99C2)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Interest", color = Color.White)
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { showToast(context, "wih kamu menolak ${notification.name} untuk ${notification.goal}") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(2.dp, Color.LightGray)
                    ) {
                        Text(text = "Decline", color = Color.DarkGray)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .clip(RoundedCornerShape(100))
                .background(Color.LightGray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    VenectorTheme {
        MessagesPage()
    }
}
