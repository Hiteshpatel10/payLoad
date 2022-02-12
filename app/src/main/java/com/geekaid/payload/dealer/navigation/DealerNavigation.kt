package com.geekaid.payload.dealer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.geekaid.payload.dealer.ui.authScreens.DealerSignInScreen
import com.geekaid.payload.dealer.ui.authScreens.DealerSignUpScreen
import com.geekaid.payload.dealer.ui.mainScreens.DealerDashboardScreen

@Composable
fun DealerNavigation( navController: NavHostController) {

    NavHost(navController = navController, startDestination = DealerScreens.SignIn.route ){

        composable(route = DealerScreens.SignIn.route){
            DealerSignInScreen(navController = navController)
        }

        composable(route = DealerScreens.SignUp.route){
            DealerSignUpScreen(navController = navController)
        }

        composable(route = DealerScreens.DashboardScreen.route){
            DealerDashboardScreen()
        }

    }
}