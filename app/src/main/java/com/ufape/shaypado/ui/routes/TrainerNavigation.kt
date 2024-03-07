package com.ufape.shaypado.ui.routes

import HomeUserLogadoScreen
import PetNvlScreen
import SocialNetworkScreen
import UserDetailsPersonalScreen
import UserTrainingScreen
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ufape.shaypado.R
import com.ufape.shaypado.ui.components.AppSnackBar
import com.ufape.shaypado.ui.screens.trainer.classDetails.ClassDetailsScreen
import com.ufape.shaypado.ui.screens.trainer.counter.CounterBase
import com.ufape.shaypado.ui.screens.trainer.createClass.CreateClassViewModel
import com.ufape.shaypado.ui.screens.trainer.createClass.CreateClassesScreen
import com.ufape.shaypado.ui.screens.trainer.createTrainings.CreateTrainingsScreen
import com.ufape.shaypado.ui.screens.trainer.createUser.AddUserScreen
import com.ufape.shaypado.ui.screens.trainer.editClass.EditClassScreen
import com.ufape.shaypado.ui.screens.trainer.editClass.EditClassViewModel
import com.ufape.shaypado.ui.screens.trainer.friends.FriendsScreen
import com.ufape.shaypado.ui.screens.trainer.home.TrainerHomeScreen
import com.ufape.shaypado.ui.screens.trainer.importFriends.ImportFriendsScreen
import com.ufape.shaypado.ui.screens.trainer.settings.SettingsScreen
import com.ufape.shaypado.ui.screens.trainer.studentDetails.StudentDetailsScreen
import com.ufape.shaypado.ui.screens.trainer.updateProfile.UpdateTrainerProfileScreen
import com.ufape.shaypado.ui.screens.trainer.updateWorkout.UpdateWorkoutScreen
import com.ufape.shaypado.ui.screens.trainer.userFriends.UserFriendsScreen
import com.ufape.shaypado.ui.screens.trainer.userPersonalList.UserPersonalListScreen
import com.ufape.shaypado.ui.screens.trainer.userProfile.UserProfileScreen
import com.ufape.shaypado.ui.screens.trainer.userProfile.UserWorkoutScreen
import com.ufape.shaypado.ui.screens.trainer.workouts.WorkoutsScreen


sealed class TrainerNavigationScreen(
    val route: String,
    val barItemStyle: BottomBarItemStyle? = null,
    val shortName: String = "",
) {
    data object NavRoot : TrainerNavigationScreen(
        "trainer_root",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weight, R.drawable.ic_nav_weight)
    )

    data object Classes : TrainerNavigationScreen(
        "trainer_classes",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_weight, R.drawable.ic_nav_weight)
    )

    data object Networking : TrainerNavigationScreen(
        "trainer_networking",
        BottomBarItemStyle(R.string.home, R.drawable.ic_nav_home, R.drawable.ic_nav_home)
    )

    data object Progress : TrainerNavigationScreen(
        "trainer_progress",
        BottomBarItemStyle(R.string.progress, R.drawable.ic_nav_chart, R.drawable.ic_nav_chart)
    )

    data object Notifications : TrainerNavigationScreen(
        "trainer_notifications",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_notifications,
            R.drawable.ic_notifications
        )
    )

    data object Settings : TrainerNavigationScreen(
        "trainer_settings",
        BottomBarItemStyle(
            R.string.settings,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object UserProfile : TrainerNavigationScreen(
        "user_profile",
        BottomBarItemStyle(
            R.string.profile,
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
        "user_workout",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object UserPersonalList : TrainerNavigationScreen(
        "user_personal_list",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object UserDetailsPersonal : TrainerNavigationScreen(
        "user_details_personal",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object HomeUserLogado : TrainerNavigationScreen(
        "home_user_logado",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
    )

    data object PetNvl : TrainerNavigationScreen(
        "pet_nvl",
        BottomBarItemStyle(
            R.string.profile,
            R.drawable.ic_nav_settings,
            R.drawable.ic_nav_settings
        )
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

    data object CreateClasses : TrainerNavigationScreen(
        "create_classes",
    )

    data object Friends : TrainerNavigationScreen(
        "friends",
    )

    data object CreateUsers : TrainerNavigationScreen(
        "create_users",
    )

    data object Workouts : TrainerNavigationScreen(
        "workouts",
    )

    data object CreateWorkout : TrainerNavigationScreen(
        "workouts_create",
    )

    data object UpdateProfile : TrainerNavigationScreen(
        "update_profile",
    )

    data object EditWorkout : TrainerNavigationScreen(
        "edit_workout/{workoutId}",
        shortName = "edit_workout"
    )
    data object ImportFriends : TrainerNavigationScreen(
        "import_friends",
    )
}

@Composable
fun TrainerBottomBar(navController: NavHostController) {
    val bottomTabItems = listOf(
        TrainerNavigationScreen.Networking,
        TrainerNavigationScreen.Progress,
        TrainerNavigationScreen.Classes,
        TrainerNavigationScreen.Notifications,
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
    snackBarMessage: String? = null,
    resetSnackBarMessage: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    AppSnackBar(
        snackBarMessage,
        { resetSnackBarMessage?.invoke() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
fun TrainerRoutes(
    logOutAction: () -> Unit
) {
    val navController: NavHostController = rememberNavController()
    val createClassViewModel = hiltViewModel<CreateClassViewModel>()
    val editClassViewModel = hiltViewModel<EditClassViewModel>()
    var snackbarMessage: String? by remember { mutableStateOf(null) }

    fun showSnackBar(message: String) {
        snackbarMessage = message
    }

    fun resetSnackBarMessage() {
        snackbarMessage = null
    }

    Scaffold(
        bottomBar = { TrainerBottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = TrainerNavigationScreen.Classes.route,
            Modifier.padding(innerPadding)
        ) {
            composable(TrainerNavigationScreen.Classes.route) {
                Container {
                    TrainerHomeScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.HomeUserLogado.route) {
                Container {
                    HomeUserLogadoScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.UserProfile.route) {
                Container {
                    UserProfileScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.SocialNetwork.route) {
                Container {
                    SocialNetworkScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.PetNvl.route) {
                Container {
                    PetNvlScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.UserProfile.route) {
                Container {
                    UserDetailsPersonalScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.UserPersonalList.route) {
                Container {
                    UserPersonalListScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.UserWorkout.route) {
                Container {
                    UserWorkoutScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.UserFriends.route) {
                Container {
                    UserFriendsScreen(
                        navController
                    )
                }
            }

            composable(TrainerNavigationScreen.UserTraining.route) {
                Container {
                    UserTrainingScreen(
                        navController
                    )
                }
            }


            composable(TrainerNavigationScreen.ClassDetails.route) {
                Container {
                    ClassDetailsScreen(navController)
                }
            }

            composable(TrainerNavigationScreen.EditClass.route) {
                Container {
                    EditClassScreen(navController, editClassViewModel)
                }
            }

            composable(TrainerNavigationScreen.StudentDetails.route) {
                Container {
                    StudentDetailsScreen(navController)
                }
            }

            composable(TrainerNavigationScreen.Networking.route) {
                Progress(navController)
            }

            composable(TrainerNavigationScreen.Progress.route) {
                Progress(navController)
            }

            composable(TrainerNavigationScreen.Notifications.route) {
                Profile(navController)
            }

            composable(TrainerNavigationScreen.Settings.route) {
                Container {
                    SettingsScreen(
                        navController = navController,
                        onLogout = logOutAction
                    )
                }
            }

            composable(TrainerNavigationScreen.CreateClasses.route) {
                Container {
                    CreateClassesScreen(
                        navController = navController,
                        createClassViewModel = createClassViewModel
                    )
                }
            }

            composable(TrainerNavigationScreen.Friends.route) {
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = { resetSnackBarMessage() }
                ) {
                    FriendsScreen(
                        navController = navController,
                        showSnackbar = { message -> showSnackBar(message) }
                    )
                }
            }

            composable(TrainerNavigationScreen.CreateUsers.route) {
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = { resetSnackBarMessage() }
                ) {
                    AddUserScreen(
                        navController = navController,
                        showSnackBar = { message -> showSnackBar(message) }
                    )
                }
            }

            composable(TrainerNavigationScreen.Workouts.route) {
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = { resetSnackBarMessage() }
                ) {
                    WorkoutsScreen(
                        navController = navController,
                        showSnackBar = { message -> showSnackBar(message) }
                    )
                }
            }

            composable(TrainerNavigationScreen.CreateWorkout.route) {
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = { resetSnackBarMessage() }
                ) {
                    CreateTrainingsScreen(
                        navController = navController,
                        showSnackBar = { message -> showSnackBar(message) }
                    )
                }
            }

            composable(TrainerNavigationScreen.UpdateProfile.route) {
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = { resetSnackBarMessage() }
                ) {
                    UpdateTrainerProfileScreen(
                        navController = navController,
                        showSnackBar = { message -> showSnackBar(message) }
                    )
                }
            }

            composable(
                TrainerNavigationScreen.EditWorkout.route,
            ) { backStackEntry ->
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = { resetSnackBarMessage() }
                ) {
                    UpdateWorkoutScreen(
                        navController = navController,
                        showSnackBar = { message -> showSnackBar(message) },
                        workoutId = backStackEntry.arguments?.getString("workoutId")!!
                    )
                }
            }

            composable(TrainerNavigationScreen.ImportFriends.route) {
                Container(
                    snackBarMessage = snackbarMessage,
                    resetSnackBarMessage = { resetSnackBarMessage() }
                ) {
                    ImportFriendsScreen(
                        navController = navController,
                        onImport = editClassViewModel::importFriends
                    )
                }
            }
        }
    }
}
