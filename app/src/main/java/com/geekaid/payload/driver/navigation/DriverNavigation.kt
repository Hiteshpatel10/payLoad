package com.geekaid.payload.driver.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geekaid.payload.driver.ui.authScreens.DriverSignInScreen
import com.geekaid.payload.driver.ui.authScreens.DriverSignUpScreen
import com.geekaid.payload.driver.ui.mainScreens.DriverAssignDealsScreen
import com.geekaid.payload.driver.ui.mainScreens.DriverDashboardScreen
import com.geekaid.payload.driver.ui.mainScreens.DriverReqDealsScreen
import com.geekaid.payload.ui.authScreens.DriverSignUpDataScreen
import com.geekaid.payload.viewmodel.DriverViewModel
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterialApi
@Composable
fun DriverNavigation(navController: NavHostController) {

    val auth = FirebaseAuth.getInstance()
    val driverViewModel: DriverViewModel = viewModel()

    val driverStartDestination = if (auth.currentUser != null) DriverScreens.DashboardScreen.route else DriverScreens.SignIn.route

    NavHost(navController = navController, startDestination = driverStartDestination) {

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
            DriverDashboardScreen(driverViewModel = driverViewModel, navController = navController)
        }

        composable(DriverScreens.AssignDealsScreen.route) {
            DriverAssignDealsScreen(driverViewModel = driverViewModel, navController = navController)
        }

        composable(DriverScreens.ReqDealsScreen.route) {
            DriverReqDealsScreen(driverViewModel = driverViewModel, navController = navController)
        }


    }

}