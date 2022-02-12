package com.geekaid.payload.dealer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geekaid.payload.dealer.ui.authScreens.DealerSignInScreen
import com.geekaid.payload.dealer.ui.authScreens.DealerSignUpScreen
import com.geekaid.payload.dealer.ui.mainScreens.DealerDashboardScreen
import com.geekaid.payload.dealer.ui.mainScreens.DealerDealAdd
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DealerNavigation( navController: NavHostController) {

    val auth = FirebaseAuth.getInstance()

    val dealerStartDestination = if (auth.currentUser != null) DealerScreens.DashboardScreen.route else DealerScreens.SignIn.route

    NavHost(navController = navController, startDestination = dealerStartDestination){

        composable(route = DealerScreens.SignIn.route){
            DealerSignInScreen(navController = navController)
        }

        composable(route = DealerScreens.SignUp.route){
            DealerSignUpScreen(navController = navController)
        }

        composable(route = DealerScreens.DashboardScreen.route){
            DealerDashboardScreen(navController = navController)
        }

        composable(route = DealerScreens.DealerDealAddScreen.route){
            DealerDealAdd(navController = navController)
        }

    }
}