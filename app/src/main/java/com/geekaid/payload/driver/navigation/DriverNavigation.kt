package com.geekaid.payload.driver.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geekaid.payload.driver.ui.authScreens.DriverSignInScreen
import com.geekaid.payload.driver.ui.authScreens.DriverSignUpDataScreen
import com.geekaid.payload.driver.ui.authScreens.DriverSignUpScreen
import com.geekaid.payload.driver.ui.mainScreens.DriverDashboardScreen

@Composable
fun DriverNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = DriverScreens.SignIn.route) {

        composable(DriverScreens.SignIn.route) {
            DriverSignInScreen(navController = navController)
        }

        composable(DriverScreens.SignUp.route) {
            DriverSignUpScreen(navController = navController)
        }

        composable(DriverScreens.SignUpData.route) {
            DriverSignUpDataScreen()
        }

        composable(DriverScreens.Dashboard.route) {
            DriverDashboardScreen()
        }


    }

}