package com.geekaid.payload

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.geekaid.payload.driver.navigation.DriverScreens

@Composable
fun DriverOrDealer(sharedPreference: SharedPreferences, navController: NavController) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            with (sharedPreference.edit()) {
                putString("driverOrDealer", "Dealer")
                apply()
            }
            navController.navigate(DealerScreens.SignIn.route)
        }) {
            Text(text = "Dealer")
        }

        Button(onClick = {
            with (sharedPreference.edit()) {
                putString("driverOrDealer", "Driver")
                apply()
            }
            navController.navigate(DriverScreens.SignIn.route)
        }) {
            Text(text = "Driver")
        }
    }
}