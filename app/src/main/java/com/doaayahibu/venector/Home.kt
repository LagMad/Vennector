package com.doaayahibu.venector

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.SettingsOverscan
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.doaayahibu.venector.ui.components.BestProductCard
import com.doaayahibu.venector.ui.components.ButuhCard
import com.doaayahibu.venector.ui.components.StatusTransaksiCard
import com.doaayahibu.venector.ui.theme.VenectorTheme

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VenectorTheme {
                HomePage()
            }
        }
    }
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
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        VenPay()
                    }
                    item {
                        MentorBanner()
                    }
                    item {
                        KategoriKebutuhan()
                    }
                    item {
                        StatusTransaksi()
                    }
                    item {
                        ProdukTerbaikBulanIni()
                    }
                    item { 
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBar(context: Context) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),

        title = {},

        navigationIcon = {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Text(text = "Selamat datang, Doa Ayah Ibu", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Thin)
                Text(text = "Sudah kayak kah kamu hari ini?", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Black)
            }
        },

        actions = {
            Row(
                modifier = Modifier
                    .padding(end = 10.dp)
            ) {
                IconButton(onClick = { showToast(context, "Favorite Icon clicked") }) {
                    Icon(Icons.Filled.Settings, contentDescription = "Action Icon")
                }
                IconButton(onClick = { showToast(context, "Search Icon clicked") }) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Action Icon")
                }
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
fun VenPay() {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
//            .offset()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 30.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.MonetizationOn, contentDescription = "Money", tint = Color(0xFF5B99C2), modifier = Modifier.size(40.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy((-7).dp)
                    ) {
                        Text(text = "VenPay TopUp", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(text = "100.000", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.SettingsOverscan, contentDescription = "Money", tint = Color(0xFF5B99C2), modifier = Modifier.size(40.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy((-7).dp)
                    ) {
                        Text(text = "VenPay", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Scan QRIS", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.Payment, contentDescription = "Money", tint = Color(0xFF5B99C2), modifier = Modifier.size(40.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy((-7).dp)
                    ) {
                        Text(text = "VenPay Transfer", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(text = "bank", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.Payments, contentDescription = "Money", tint = Color(0xFF5B99C2), modifier = Modifier.size(40.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy((-7).dp)
                    ) {
                        Text(text = "VenPay", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Tarik tunai", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun MentorBanner() {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF1A4870))
//                .clip(RoundedCornerShape(12.dp)) // Ensures overflow is clipped
//                .overflow(Overflow.Hidden)
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFF1A4870))
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Text(
                        text = "Mentor With Haga\nHanya 30k ajah",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Left,
                        color = Color.White
                    )

                    Button(
                        onClick = { showToast(context, "hago dipesan :p") },
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF9DBBA)
                        ),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Text(
                            text = "Book Here",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Mentor Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(180.dp) // Define the size of the image
                        .align(alignment = Alignment.Bottom) // Position at bottom-right
                        .offset(x = 0.dp, y = 0.dp) // Adjust offset if necessary
                        .zIndex(-1f) // Ensure it does not overlap with the `Row`
                )
            }
        }
    }
}


@Composable
fun KategoriKebutuhan() {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Kamu lagi butuh apa nih?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1F316F)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ButuhCard("MENTORING", R.drawable.home, modifier = Modifier.weight(1f), onClick = { showToast(context, "Mentoring") })
            ButuhCard("FINANCING", R.drawable.home, modifier = Modifier.weight(1f), onClick = { context.startActivity(Intent(context, Feeds::class.java)) })
        }
    }
}

@Composable
fun StatusTransaksi() {
    Column {
        Text(
            text = "Status Transaksi",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        StatusTransaksiCard("Sepatu adudusnya haga")
        StatusTransaksiCard("Topi ronaldo polanski")
    }
}

@Composable
fun ProdukTerbaikBulanIni() {
    data class ProductData(
        val title: String,
        val imageRes: Int
    )

    val products = listOf(
        ProductData("Bola Ronaldo Saputro", R.drawable.cart),
        ProductData("Sepatu adudus hago", R.drawable.cart),
        ProductData("Mouse acel sinaga", R.drawable.cart),
        ProductData("Gelas suropati", R.drawable.cart),
        ProductData("JBL GO kemerdekaan RI", R.drawable.cart),
        ProductData("Teko jeremiansyah", R.drawable.cart),
        ProductData("Roti Girl", R.drawable.cart)
    )

    Column(modifier = Modifier.padding(0.dp)) {
        Text(
            text = "Produk terbaik Bulan ini",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            products.chunked(2).forEach { rowItems ->
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    // Iterate over each product in the row and render the BestProductCard
                    rowItems.forEach { product ->
                        BestProductCard(
                            title = product.title,
                            imageRes = product.imageRes,
                            modifier = Modifier
                                .weight(1f) // Ensure equal width for all items in the row
                        )
                    }

                    if (rowItems.size == 1) {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .weight(1f),
                        )
                    }
                }
            }
        }
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
//
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.MonetizationOn
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material.icons.outlined.MonetizationOn
//import androidx.compose.material.icons.outlined.Payment
//import androidx.compose.material.icons.outlined.Payments
//import androidx.compose.material.icons.outlined.SettingsOverscan
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.doaayahibu.venector.ui.theme.VenectorTheme
//
//class Home : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            VenectorTheme {
//                HomePage()
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MediumTopAppBar(context: Context) {
//    TopAppBar(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp),
//
//        title = {},
//
//        navigationIcon = {
//            Column(
//                modifier = Modifier
//                    .padding(start = 10.dp)
//            ) {
//                Text(text = "Selamat datang, Doa Ayah Ibu", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Thin)
//                Text(text = "Sudah kayak kah kamu hari ini?", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Black)
//            }
//        },
//
//        actions = {
//            Row(
//                modifier = Modifier
//                    .padding(end = 10.dp)
//            ) {
//                IconButton(onClick = { showToast(context, "Favorite Icon clicked") }) {
//                    Icon(Icons.Filled.Settings, contentDescription = "Action Icon")
//                }
//                IconButton(onClick = { showToast(context, "Search Icon clicked") }) {
//                    Icon(Icons.Filled.Notifications, contentDescription = "Action Icon")
//                }
//            }
//        },
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = Color(0xFF5B99C2),
//            titleContentColor = Color(0xFFFFFFFF),
//            actionIconContentColor = Color.White,
//            navigationIconContentColor = Color.White
//        )
//    )
//}
//
//@Composable
//fun HomePage() {
//    val context = LocalContext.current
//
//    Scaffold(
//        topBar = { MediumTopAppBar(context) },
//        bottomBar = { BottomNavigationBar(context) },
//        content = { paddingValues ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(Color.White)
//                    .padding(paddingValues)
//            ) {
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 8.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    item {
//                        VenPay()
//                    }
//                    item {
//                        Carousel()
//                    }
//                }
//            }
//        }
//    )
//}
//
//@Composable
//fun VenPay() {
//    Box(
//        modifier = Modifier
//            .shadow(
//                elevation = 20.dp, // Elevation defines the intensity of the shadow
//                shape = RoundedCornerShape(16.dp), // Shape of the shadow (optional)
//                clip = false // Clip determines if content is clipped to the shape
//            )
////            .offset()
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    color = Color.White, // Background color
//                    shape = RoundedCornerShape(8.dp) // Optional: Apply rounded corners
//                )
//                .padding(horizontal = 30.dp, vertical = 10.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            Column(
//                verticalArrangement = Arrangement.spacedBy(5.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(5.dp)
//                ) {
//                    Icon(imageVector = Icons.Outlined.MonetizationOn, contentDescription = "Money", tint = Color(0xFF5B99C2), modifier = Modifier.size(40.dp))
//                    Column(
//                        verticalArrangement = Arrangement.spacedBy(-7.dp)
//                    ) {
//                        Text(text = "VenPay TopUp", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
//                        Text(text = "100.000", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
//                    }
//                }
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(5.dp)
//                ) {
//                    Icon(imageVector = Icons.Outlined.SettingsOverscan, contentDescription = "Money", tint = Color(0xFF5B99C2), modifier = Modifier.size(40.dp))
//                    Column(
//                        verticalArrangement = Arrangement.spacedBy(-7.dp)
//                    ) {
//                        Text(text = "VenPay", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
//                        Text(text = "Scan QRIS", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
//                    }
//                }
//            }
//
//            Column(
//                verticalArrangement = Arrangement.spacedBy(5.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(5.dp)
//                ) {
//                    Icon(imageVector = Icons.Outlined.Payment, contentDescription = "Money", tint = Color(0xFF5B99C2), modifier = Modifier.size(40.dp))
//                    Column(
//                        verticalArrangement = Arrangement.spacedBy(-7.dp)
//                    ) {
//                        Text(text = "VenPay Transfer", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
//                        Text(text = "bank", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
//                    }
//                }
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(5.dp)
//                ) {
//                    Icon(imageVector = Icons.Outlined.Payments, contentDescription = "Money", tint = Color(0xFF5B99C2), modifier = Modifier.size(40.dp))
//                    Column(
//                        verticalArrangement = Arrangement.spacedBy(-7.dp)
//                    ) {
//                        Text(text = "VenPay", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
//                        Text(text = "Tarik tunai", color = Color.DarkGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Carousel() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 12.dp)
//            .background(Color.White, shape = RoundedCornerShape(8.dp))
//    ) {
//
//    }
//}
//

//
//fun showToast(context: Context, message: String) {
//    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    VenectorTheme {
//        HomePage()
//    }
//}