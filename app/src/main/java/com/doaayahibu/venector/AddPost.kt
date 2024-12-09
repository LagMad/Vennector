package com.doaayahibu.venector

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.doaayahibu.venector.ui.theme.VenectorTheme

class AddPost : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VenectorTheme {
                AddPostPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostPage() {
    val context = LocalContext.current
    val imageUris = remember { mutableStateListOf<Uri>() }
    val inputTitle = remember { mutableStateOf("") }
    val inputDescription = remember { mutableStateOf("") }

    // ActivityResultLauncher to pick multiple images
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        if (uris.isNotEmpty()) {
            imageUris.clear() // Clear the list to avoid duplicates
            imageUris.addAll(uris) // Add the newly selected URIs
        }
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    IconButton(onClick = {
                        showToast(context, "Navigation Icon clicked")
                        context.startActivity(Intent(context, Feeds::class.java))
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Navigation Icon", tint = Color.Black)
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Import button
                    Button(
                        onClick = { launcher.launch("image/*") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A4870),
                        )
                    ) {
                        Text(text = "Import Pictures", color = Color.White)
                    }

                    // Horizontal scrolling images
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(imageUris) { uri ->
                            Image(
                                painter = rememberAsyncImagePainter(uri),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Gray)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    Text(text = "Title", color = Color(0xFF1A4870), fontSize = 24.sp, fontWeight = FontWeight.Bold)

                    TextField(
                        value = inputTitle.value,
                        onValueChange = { inputTitle.value = it },
                        label = { Text("Enter text here") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            containerColor = Color.Transparent,
                            unfocusedLabelColor = Color.DarkGray,
                            focusedLabelColor = Color.DarkGray,
                            focusedIndicatorColor = Color.Black,
                            cursorColor = Color.Black
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    Text(text = "Caption", color = Color(0xFF1A4870), fontSize = 24.sp, fontWeight = FontWeight.Bold)

                    TextField(
                        value = inputDescription.value,
                        onValueChange = { inputDescription.value = it },
                        label = { Text("Enter text here") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            containerColor = Color.Transparent,
                            unfocusedLabelColor = Color.DarkGray,
                            focusedLabelColor = Color.DarkGray,
                            focusedIndicatorColor = Color.Black,
                            cursorColor = Color.Black
                        )
                    )
                }
                
                Button(
                    onClick = { showToast(context, "Upload clicked") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A4870)
                    )
                ) {
                    Text(text = "Upload Now", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    )
}




@Preview(showBackground = true)
@Composable
fun AddPostPagePreview() {
    VenectorTheme {
        AddPostPage()
    }
}