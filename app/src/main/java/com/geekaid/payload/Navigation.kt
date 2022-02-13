package com.geekaid.payload

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
import com.geekaid.payload.ui.authScreens.DriverSignUpDataScreen
import com.geekaid.payload.viewmodel.DealerViewModel
import com.geekaid.payload.viewmodel.DriverViewModel
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {

    val auth = FirebaseAuth.getInstance()
    val dealerViewModel: DealerViewModel = viewModel()

    val driverViewModel: DriverViewModel = viewModel()
    val driverStartDestination = if (auth.currentUser != null) DriverScreens.DashboardScreen.route else DriverScreens.SignIn.route

    val dealerStartDestination =
        if (auth.currentUser != null) DealerScreens.DashboardScreen.route else DealerScreens.SignIn.route

    NavHost(navController = navController, startDestination = dealerStartDestination) {

        //Dealers
        composable(route = DealerScreens.SignIn.route) {
            DealerSignInScreen(navController = navController)
        }

        composable(route = DealerScreens.SignUp.route) {
            DealerSignUpScreen(navController = navController)
        }

        composable(route = DealerScreens.DashboardScreen.route) {
            DealerDashboardScreen(dealerViewModel = dealerViewModel, navController = navController)
        }

        composable(route = DealerScreens.DealerDealAddScreen.route) {
            DealerDealAdd(navController = navController)
        }

        composable(route = DealerScreens.DealerDriverListScreen.route) {
            DealerDriverListScreen(dealerViewModel = dealerViewModel, navController = navController)
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