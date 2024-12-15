package com.doaayahibu.venector.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.showToast

@Composable
fun TabButton(
    label: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Button(
        onClick = {
            showToast(context, "Recent button clicked")
        },
        modifier = modifier
//            .weight(1f) // Use this oke oke
            .padding(vertical = 0.dp)
            .padding(horizontal = 0.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFFFF6500)
        ),
        border = BorderStroke(2.dp, Color(0xFFA8CBDE))
    ) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}