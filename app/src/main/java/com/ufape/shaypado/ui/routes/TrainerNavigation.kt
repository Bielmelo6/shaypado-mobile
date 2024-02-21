package com.ufape.shaypado.ui.routes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R


sealed class TrainerNavigationScreen(val route: String, val barItemStyle: BottomBarItemStyle) {
    data object NavRoot : TrainerNavigationScreen(
        "trainer_root",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weigth, R.drawable.ic_nav_weigth)
    )

    data object Home : TrainerNavigationScreen(
        "trainer_home",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weigth, R.drawable.ic_nav_weigth)
    )

    data object Pet : TrainerNavigationScreen(
        "trainer_pet",
        BottomBarItemStyle(R.string.pet, R.drawable.ic_nav_paw, R.drawable.ic_nav_paw)
    )

    data object Progress : TrainerNavigationScreen(
        "trainer_progress",
        BottomBarItemStyle(R.string.progress, R.drawable.ic_nav_chart, R.drawable.ic_nav_chart)
    )

    data object Profile : TrainerNavigationScreen(
        "trainer_profile",
        BottomBarItemStyle(R.string.profile, R.drawable.ic_nav_user, R.drawable.ic_nav_user)
    )

    data object Settings : TrainerNavigationScreen(
        "trainer_settings",
        BottomBarItemStyle(R.string.settings, R.drawable.ic_nav_nut, R.drawable.ic_nav_nut)
    )
}

@Composable
fun TrainerRoutes(
    logOutAction: () -> Unit
) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = TrainerNavigationScreen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(TrainerNavigationScreen.Home.route) {
                Home(navController, logOutAction)
            }
            composable(TrainerNavigationScreen.Pet.route) {
                Pet(navController)
            }
            composable(TrainerNavigationScreen.Progress.route) {
                Progress(navController)
            }
            composable(TrainerNavigationScreen.Profile.route) {
                Profile(navController)
            }
            composable(TrainerNavigationScreen.Settings.route) {
                Settings(navController)
            }
        }
    }
}
