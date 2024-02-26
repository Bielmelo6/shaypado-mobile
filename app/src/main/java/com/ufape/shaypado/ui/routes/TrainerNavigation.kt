package com.ufape.shaypado.ui.routes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.screens.trainer.classDetails.ClassDetailsScreen
import com.ufape.shaypado.ui.screens.trainer.editClass.EditClassScreen
import com.ufape.shaypado.ui.screens.trainer.home.TrainerHomeScreen
import com.ufape.shaypado.ui.screens.trainer.studentDetails.StudentDetailsScreen


sealed class TrainerNavigationScreen(
    val route: String,
    val barItemStyle: BottomBarItemStyle? = null
) {
    data object NavRoot : TrainerNavigationScreen(
        "trainer_root",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weight, R.drawable.ic_nav_weight)
    )

    data object Home : TrainerNavigationScreen(
        "trainer_home",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weight, R.drawable.ic_nav_weight)
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

    data object ClassDetails : TrainerNavigationScreen(
        "class_details",
    )

    data object EditClass : TrainerNavigationScreen(
        "class_edit",
    )

    data object StudentDetails : TrainerNavigationScreen(
        "student_details",
    )
}

@Composable
fun TrainerBottomBar(navController: NavHostController) {
    val bottomTabItems = listOf(
        TrainerNavigationScreen.Progress,
        TrainerNavigationScreen.Home,
        TrainerNavigationScreen.Profile,
        TrainerNavigationScreen.Settings,
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
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        bottomTabItems.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            screen.barItemStyle?.let { BottomTabItem(isSelected, it, navController, screen.route) }
        }
    }
}

@Composable
fun Container(
    content : @Composable () -> Unit
){
    Column(modifier = Modifier.padding(16.dp)) {
        content()
    }
}

@Composable
fun TrainerRoutes(
    logOutAction: () -> Unit
) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = { TrainerBottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = TrainerNavigationScreen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(TrainerNavigationScreen.Home.route) {
                Container {
                    TrainerHomeScreen(
                        navController
                    )
                }
            }

            composable (TrainerNavigationScreen.ClassDetails.route) {
                Container {
                    ClassDetailsScreen(navController)
                }
            }

            composable (TrainerNavigationScreen.EditClass.route) {
                Container {
                    EditClassScreen(navController)
                }
            }

            composable (TrainerNavigationScreen.StudentDetails.route) {
                Container {
                    StudentDetailsScreen(navController)
                }
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
