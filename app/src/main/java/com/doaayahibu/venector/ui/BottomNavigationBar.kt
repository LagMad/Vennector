package com.doaayahibu.venector.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.doaayahibu.venector.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val tabs = listOf(
        BottomNavItem("home", R.drawable.home, R.drawable.home_before, "Home"),
        BottomNavItem("chat", R.drawable.chat, R.drawable.chat_before, "Chat"),
        BottomNavItem("shop", R.drawable.shop, R.drawable.shop_before, "Shop"),
        BottomNavItem("profile", R.drawable.user, R.drawable.user_before, "Profile")
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        tabs.forEach { tab ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (currentRoute == tab.route) tab.selectedIcon else tab.unselectedIcon
                        ),
                        contentDescription = tab.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(tab.label) },
                selected = currentRoute == tab.route,
                onClick = {
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val label: String
)
