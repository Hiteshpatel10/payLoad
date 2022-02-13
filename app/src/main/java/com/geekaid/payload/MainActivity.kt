package com.geekaid.payload

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.geekaid.payload.components.DealerBottomNav
import com.geekaid.payload.components.DriverBottomNav
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.geekaid.payload.driver.navigation.DriverScreens
import com.geekaid.payload.ui.theme.PayLoadTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreference: SharedPreferences = this.getPreferences(Context.MODE_PRIVATE)
        val dealerOrDriver = sharedPreference.getString("driverOrDealer", "")
        setContent {
            PayLoadTheme {
                Surface {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            if (bottomNavVisibility(navController = navController)) {
                                if (dealerOrDriver == "Dealer")
                                    DealerBottomNav(navController = navController)
                                else
                                    DriverBottomNav(navController)
                            }
                        }
                    ) {
                        MainNavigation(
                            navController = navController,
                            sharedPreferences = sharedPreference
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun bottomNavVisibility(navController: NavController): Boolean {

    var isBottomNavVisible by remember { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        DealerScreens.SignIn.route -> isBottomNavVisible = false
        DealerScreens.SignUp.route -> isBottomNavVisible = false
        DriverScreens.SignIn.route -> isBottomNavVisible = false
        DriverScreens.SignUp.route -> isBottomNavVisible = false
        "driverOrDealer" -> isBottomNavVisible = false
        DealerScreens.DashboardScreen.route -> isBottomNavVisible = true
        DriverScreens.DashboardScreen.route -> isBottomNavVisible = true

    }
    return isBottomNavVisible
}



