package com.geekaid.payload.dealer.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geekaid.payload.dealer.ui.authScreens.DealerSignInScreen
import com.geekaid.payload.dealer.ui.authScreens.DealerSignUpScreen
import com.geekaid.payload.dealer.ui.mainScreens.DealerDashboardScreen
import com.geekaid.payload.dealer.ui.mainScreens.DealerDealAdd
import com.geekaid.payload.dealer.ui.mainScreens.DealerDriverListScreen
import com.geekaid.payload.viewmodel.DealerViewModel
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterialApi
@Composable
fun DealerNavigation(navController: NavHostController) {

    val auth = FirebaseAuth.getInstance()
    val dealerViewModel: DealerViewModel = viewModel()

    val dealerStartDestination =
        if (auth.currentUser != null) DealerScreens.DashboardScreen.route else DealerScreens.SignIn.route

    NavHost(navController = navController, startDestination = dealerStartDestination) {

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
            DealerDriverListScreen(dealerViewModel = dealerViewModel, navController = navController )
        }

    }
}