package com.geekaid.payload

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.geekaid.payload.components.DealerBottomNav
import com.geekaid.payload.components.DriverBottomNav
import com.geekaid.payload.dealer.navigation.DealerNavigation
import com.geekaid.payload.ui.theme.PayLoadTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PayLoadTheme {

                Surface {
                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = {
//                            DriverBottomNav(navController)
                                DealerBottomNav(navController = navController)
                        }
                    ) {

//                        DealerNavigation(navController = navController)
//                        DriverNavigation(navController = navController)
                            Navigation(navController = navController)

                    }
                }
            }
        }
    }
}

