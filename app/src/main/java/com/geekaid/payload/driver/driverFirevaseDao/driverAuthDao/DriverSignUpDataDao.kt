package com.geekaid.payload.driver.driverFirevaseDao.driverAuthDao

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.geekaid.payload.driver.model.DriverRouteList
import com.geekaid.payload.driver.navigation.DriverScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun driverSignUpDataDao(
    credentials: DriverRouteList,
    context: Context,
    navController: NavController
) {

    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore

    if (validateSignUpRouteData(context, credentials)) {


        try {
            firestore.collection("users").document("driver")
                .collection(auth.currentUser?.email.toString()).document("routes")
                .set(credentials)
                .addOnSuccessListener {
                    Toast.makeText(context, "Routes Saved", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            navController.navigate(DriverScreens.DashboardScreen.route)
        } catch (e: Exception) {
            Log.i("testMain", "dflkjadghfajshgj")
            Log.i("testMain", "${auth.currentUser?.email}")
            Log.i("testMain", "${e.message}")
        }
    }

}


fun validateSignUpRouteData(context: Context, credential: DriverRouteList): Boolean {

    var boolean = false
    credential.routeList.forEach { routeData ->

        boolean = true

        when {

            routeData.fromState.isEmpty() -> {
                Toast.makeText(
                    context,
                    "From state can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
                boolean = false
            }

            routeData.fromCity.isEmpty() -> {
                Toast.makeText(
                    context,
                    "From city can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
                boolean = false
            }

            routeData.toState.isEmpty() -> {
                Toast.makeText(
                    context,
                    "To state can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
                boolean = false
            }

            routeData.toCity.isEmpty() -> {
                Toast.makeText(
                    context,
                    "To city can't be empty",
                    Toast.LENGTH_SHORT
                ).show()
                boolean = false
            }

        }
    }

    return boolean
}