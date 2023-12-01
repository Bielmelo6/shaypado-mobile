package com.ufape.shaypado.ui.routes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R

data class BottomBarItemStyle(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconFocused: Int,
)

sealed class MobileNavigationScreen(val route: String, val barItemStyle: BottomBarItemStyle) {
    data object NavRoot : MobileNavigationScreen(
        "mobile_root",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weigth, R.drawable.ic_nav_weigth)
    )

    data object Home : MobileNavigationScreen(
        "home",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weigth, R.drawable.ic_nav_weigth)
    )

    data object Pet : MobileNavigationScreen(
        "pet",
        BottomBarItemStyle(R.string.pet, R.drawable.ic_nav_paw, R.drawable.ic_nav_paw)
    )

    data object Progress : MobileNavigationScreen(
        "progress",
        BottomBarItemStyle(R.string.progress, R.drawable.ic_nav_chart, R.drawable.ic_nav_chart)
    )

    data object Profile : MobileNavigationScreen(
        "profile",
        BottomBarItemStyle(R.string.profile, R.drawable.ic_nav_user, R.drawable.ic_nav_user)
    )

    data object Settings : MobileNavigationScreen(
        "settings",
        BottomBarItemStyle(R.string.settings, R.drawable.ic_nav_nut, R.drawable.ic_nav_nut)
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val bottomTabItems = listOf(
        MobileNavigationScreen.Pet,
        MobileNavigationScreen.Progress,
        MobileNavigationScreen.Home,
        MobileNavigationScreen.Profile,
        MobileNavigationScreen.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = bottomTabItems.any { it.route == currentDestination?.route }

    //Checks if the current route is in the bottom bar if not it's not displayed
    if (!bottomBarDestination) {
        return
    }

    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.primary))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomTabItems.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            BottomTabItem(isSelected, screen.barItemStyle, navController, screen.route)
        }
    }
}

@Composable
fun BottomTabItem(
    isSelected: Boolean,
    style: BottomBarItemStyle,
    navController: NavController,
    route: String
) {
    Box(modifier = Modifier
        .height(60.dp)
        .clickable(
            onClick = {
                navController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = style.icon),
                contentDescription = "icon",
                tint = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
fun MobileRoutes(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = MobileNavigationScreen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(MobileNavigationScreen.Home.route) {
                Home(navController)
            }
            composable(MobileNavigationScreen.Pet.route) {
                Pet(navController)
            }
            composable(MobileNavigationScreen.Progress.route) {
                Progress(navController)
            }
            composable(MobileNavigationScreen.Profile.route) {
                Profile(navController)
            }
            composable(MobileNavigationScreen.Settings.route) {
                Settings(navController)
            }
        }
    }
}

@Composable
fun Home(navController: NavController) {
    Text(text = "Home")
}

@Composable
fun Pet(navController: NavController) {
    Text(text = "Pet")
}

@Composable
fun Progress(navController: NavController) {
    Text(text = "Progress")
}

@Composable
fun Profile(navController: NavController) {
    Text(text = "Profile")
}

@Composable
fun Settings(navController: NavController) {
    Text(text = "Settings")
}