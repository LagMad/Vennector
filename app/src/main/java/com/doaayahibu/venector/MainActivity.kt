package com.doaayahibu.venector

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.doaayahibu.venector.ui.theme.VenectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VenectorTheme {
//                MainScreen()
                HomePage()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Text(text = "no content")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBar(context: Context) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name), style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFFFFFF))) },

        navigationIcon = {
            IconButton(onClick = { showToast(context, "Navigation Icon clicked") }) {
                Icon(Icons.Filled.Menu, contentDescription = "Navigation Icon")
            }
        },

        actions = {
            IconButton(onClick = { showToast(context, "Favorite Icon clicked") }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Action Icon")
            }
            IconButton(onClick = { showToast(context, "Search Icon clicked") }) {
                Icon(Icons.Filled.Search, contentDescription = "Action Icon")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF5B99C2),
            titleContentColor = Color(0xFFFFFFFF),
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

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
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        Carousel()
                    }
                }
            }
        }
    )
}

@Composable
fun Carousel() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {

    }
}

@Composable
fun BottomNavigationBar(context: Context) {
    BottomAppBar(
//        modifier = Modifier
//            .border(2.dp, Color.Black),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = { val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/hizkiajeremmy"))
                    context.startActivity(intent) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "Home"
                    )
                }
                IconButton(onClick = { val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/hizkiajeremmy"))
                    context.startActivity(intent) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = "Cart"
                    )
                }
                IconButton(onClick = { val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/hizkiajeremmy"))
                    context.startActivity(intent) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat),
                        contentDescription = "Chat"
                    )
                }
                IconButton(onClick = { val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/hizkiajeremmy"))
                    context.startActivity(intent) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile"
                    )
                }
            }
        },
        containerColor = Color.White,
        contentColor = Color(0xFF5B99C2)
    )
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