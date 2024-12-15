package com.doaayahibu.venector.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavArrowBackTitle(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    tint: Color = Color.Black
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // IconButton aligned to the start
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Navigation Icon",
                tint = tint
            )
        }

        // Spacer to push the Text to the center
        Spacer(modifier = Modifier.weight(1f))

        // Title Text centered horizontally
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF1A4870)
        )

        // Spacer to balance the Row layout
        Spacer(modifier = Modifier.weight(1.5f))
    }
}

