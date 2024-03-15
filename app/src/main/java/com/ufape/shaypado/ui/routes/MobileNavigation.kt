package com.ufape.shaypado.ui.routes

import HomeUserLogadoScreen
import PetNvlScreen
import SocialNetworkScreen
import UserDetailsPersonalScreen
import UserStartWorkoutScreen
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.screens.trainer.settings.SettingsScreen
import com.ufape.shaypado.ui.screens.trainer.userFriends.UserFriendsScreen
import com.ufape.shaypado.ui.screens.trainer.userPersonalList.UserPersonalListScreen
import com.ufape.shaypado.ui.screens.trainer.userProfile.UserProfileScreen
import com.ufape.shaypado.ui.screens.user.exerciseDetails.ExerciseDetailsScreen

data class BottomBarItemStyle(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val iconFocused: Int,
)

sealed class MobileNavigationScreen(
    val route: String,
    val barItemStyle: BottomBarItemStyle? = null
) {
    data object NavRoot : MobileNavigationScreen(
        "mobile_root",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weight, R.drawable.ic_nav_weight)
    )

    data object Home : MobileNavigationScreen(
        "home",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weight, R.drawable.ic_nav_weight)
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
        BottomBarItemStyle(R.string.profile, R.drawable.ic_nav_home, R.drawable.ic_nav_home)
    )

    data object Settings : MobileNavigationScreen(
        "settings",
        BottomBarItemStyle(
            R.string.settings,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object SocialNetwork : TrainerNavigationScreen(
        "social_network",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object UserTraining : TrainerNavigationScreen(
        "user_training",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object UserFriends : TrainerNavigationScreen(
        "user_friends",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object UserWorkout : TrainerNavigationScreen(
        "user_workout/{workoutId}",
        shortName = "user_workout",
        barItemStyle = BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object UserPersonalList : TrainerNavigationScreen(
        "user_personal_list",
    )

    data object UserDetailsPersonal : TrainerNavigationScreen(
        "user_details_personal",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object Notifications : TrainerNavigationScreen(
        "trainer_notifications",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_notifications,
            R.drawable.ic_notifications
        )
    )

    data object ExerciseDetails : TrainerNavigationScreen(
        "exercise_details"
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val bottomTabItems = listOf(
        MobileNavigationScreen.Profile,
        MobileNavigationScreen.Progress,
        MobileNavigationScreen.Home,
        MobileNavigationScreen.Pet,
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
fun BottomTabItem(
    isSelected: Boolean,
    style: BottomBarItemStyle,
    navController: NavController,
    route: String
) {
    if (isSelected) {
        Box(modifier = Modifier
            .zIndex(2f)
            .graphicsLayer {
                translationY = -40f
            }
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
                    .height(60.dp)
                    .width(60.dp)
                    .background(
                        shape = RoundedCornerShape(50.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    )
                    .border(
                        width = 4.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(50.dp)
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = style.icon),
                    contentDescription = "icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
        return
    }

    Box(modifier = Modifier
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
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
fun MobileRoutes(
    logOutAction: () -> Unit
) {
    val navController: NavHostController = rememberNavController()
    var snackbarMessage: String? by remember { mutableStateOf(null) }

    fun showSnackBar(message: String) {
        snackbarMessage = message
    }

    fun resetSnackBarMessage() {
        snackbarMessage = null
    }

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = MobileNavigationScreen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(MobileNavigationScreen.Home.route) {
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = ::resetSnackBarMessage
                ) {
                    HomeUserLogadoScreen(
                        navController = navController,
                        showSnackbar = ::showSnackBar
                    )
                }
            }

            composable(MobileNavigationScreen.Settings.route) {
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = ::resetSnackBarMessage
                ) {
                    SettingsScreen(
                        navController = navController,
                        onLogout = {
                            logOutAction()
                        },
                        showSnackBar = ::showSnackBar
                    )
                }
            }

            composable(MobileNavigationScreen.Profile.route) {
                Container {
                    UserProfileScreen(
                        navController
                    )
                }
            }

            composable(MobileNavigationScreen.SocialNetwork.route) {
                Container {
                    SocialNetworkScreen(
                        navController
                    )
                }
            }

            composable(MobileNavigationScreen.Pet.route) {
                    PetNvlScreen(
                        navController
                    )
            }

            composable(MobileNavigationScreen.Profile.route) {
                Container {
                    UserDetailsPersonalScreen(
                        navController
                    )
                }
            }

            composable(MobileNavigationScreen.UserPersonalList.route) {
                Container {
                    UserPersonalListScreen(
                        navController
                    )
                }
            }

            composable(MobileNavigationScreen.UserWorkout.route) { backStackEntry ->
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = ::resetSnackBarMessage
                ) {
                    UserStartWorkoutScreen(
                        navController = navController,
                        showSnackbar = ::showSnackBar,
                        workoutId = backStackEntry.arguments?.getString("workoutId")!!
                    )
                }
            }

            composable(MobileNavigationScreen.UserFriends.route) {
                Container {
                    UserFriendsScreen(
                        navController
                    )
                }
            }

            composable(MobileNavigationScreen.ExerciseDetails.route) {
                Container {
                    ExerciseDetailsScreen(
                        navController,
                        showSnackBar = ::showSnackBar,
                        exerciseId = "1"
                    )
                }
            }
        }
    }
}