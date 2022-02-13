package com.geekaid.payload

import android.content.SharedPreferences
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.geekaid.payload.dealer.ui.authScreens.DealerSignInScreen
import com.geekaid.payload.dealer.ui.authScreens.DealerSignUpScreen
import com.geekaid.payload.dealer.ui.mainScreens.DealerDashboardScreen
import com.geekaid.payload.dealer.ui.mainScreens.DealerDealAdd
import com.geekaid.payload.dealer.ui.mainScreens.DealerDriverAssignScreen
import com.geekaid.payload.dealer.ui.mainScreens.DealerDriverListScreen
import com.geekaid.payload.driver.navigation.DriverScreens
import com.geekaid.payload.driver.ui.authScreens.DriverSignInScreen
import com.geekaid.payload.driver.ui.authScreens.DriverSignUpScreen
import com.geekaid.payload.driver.ui.mainScreens.DriverAssignDealsScreen
import com.geekaid.payload.driver.ui.mainScreens.DriverDashboardScreen
import com.geekaid.payload.driver.ui.mainScreens.DriverReqDealsScreen
import com.geekaid.payload.driver.ui.authScreens.DriverSignUpDataScreen
import com.geekaid.payload.viewmodel.DealerViewModel
import com.geekaid.payload.viewmodel.DriverViewModel
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterialApi
@Composable
fun MainNavigation(navController: NavHostController, sharedPreferences: SharedPreferences) {

    var start by remember { mutableStateOf("") }

    val auth = FirebaseAuth.getInstance()
    val dealerViewModel: DealerViewModel = viewModel()
    val driverViewModel: DriverViewModel = viewModel()

    when {
        sharedPreferences.getString("driverOrDealer", "").toString()
            .isEmpty() && auth.currentUser == null -> start =
            "driverOrDealer"

        sharedPreferences.getString("driverOrDealer", "")
            .toString() == "Dealer" && auth.currentUser == null -> start =
            DealerScreens.SignIn.route

        sharedPreferences.getString("driverOrDealer", "")
            .toString() == "Driver" && auth.currentUser == null -> start =
            DriverScreens.SignIn.route

        sharedPreferences.getString("driverOrDealer", "")
            .toString() == "Dealer" && auth.currentUser != null -> start =
            DealerScreens.DashboardScreen.route

        sharedPreferences.getString("driverOrDealer", "")
            .toString() == "Driver" && auth.currentUser != null -> start =
            DriverScreens.DashboardScreen.route

    }

    if (start.isNotEmpty()) {
        NavHost(navController = navController, startDestination = start) {

            composable(route = "driverOrDealer") {
                DriverOrDealer(sharedPreference = sharedPreferences, navController = navController)
            }

            //Dealers
            composable(route = DealerScreens.SignIn.route) {
                DealerSignInScreen(navController = navController)
            }

            composable(route = DealerScreens.SignUp.route) {
                DealerSignUpScreen(navController = navController)
            }

            composable(route = DealerScreens.DashboardScreen.route) {
                DealerDashboardScreen(
                    dealerViewModel = dealerViewModel,
                    navController = navController
                )
            }

            composable(route = DealerScreens.DealerDealAddScreen.route) {
                DealerDealAdd(navController = navController)
            }

            composable(route = DealerScreens.DealerDriverListScreen.route) {
                DealerDriverListScreen(
                    dealerViewModel = dealerViewModel,
                    navController = navController
                )
            }

            composable(route = "${DealerScreens.DealerDriverAssignScreen.route}/{email}",
                arguments = listOf(
                    navArgument("email") { type = NavType.StringType }
                )) { backStackEntry ->
                DealerDriverAssignScreen(
                    email = backStackEntry.arguments?.getString("email"),
                    dealerViewModel = dealerViewModel
                )
            }

            //Driver
            composable(DriverScreens.SignIn.route) {
                DriverSignInScreen(navController = navController)
            }

            composable(DriverScreens.SignUp.route) {
                DriverSignUpScreen(navController = navController)
            }

            composable(DriverScreens.SignUpData.route) {
                DriverSignUpDataScreen(navController = navController)
            }

            composable(DriverScreens.DashboardScreen.route) {
                DriverDashboardScreen(
                    driverViewModel = driverViewModel,
                    navController = navController
                )
            }

            composable(DriverScreens.AssignDealsScreen.route) {
                DriverAssignDealsScreen(
                    driverViewModel = driverViewModel,
                    navController = navController
                )
            }

            composable(DriverScreens.ReqDealsScreen.route) {
                DriverReqDealsScreen(
                    driverViewModel = driverViewModel,
                    navController = navController
                )
            }

        }
    }

}

