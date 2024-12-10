package com.doaayahibu.venector.ui.components

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.Feeds
import com.doaayahibu.venector.InvestorDescription
import com.doaayahibu.venector.Messages
import com.doaayahibu.venector.showToast

@Composable
fun NotifCard(
    name: String,
    goal: String,
    day: String,
    time: String
) {
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
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("$name")
                        }
                        append(" wants to ") // Add the regular text before the bold part
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("$goal") // Make goal bold
                        }
                        append(" on your product!!") // Add the regular text after the bold part
                    }
                )

                Text(text = "What are you waiting for?")
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "$day at $time", color = Color.DarkGray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { showToast(context, "wih kamu tertarik $name untuk $goal") },
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
                        onClick = { showToast(context, "wih kamu menolak $name untuk $goal") },
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