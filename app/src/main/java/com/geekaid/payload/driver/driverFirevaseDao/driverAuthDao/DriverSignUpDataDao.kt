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
    routes: DriverRouteList,
    context: Context,
    navController: NavController
) {

    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore

    if (validateSignUpRouteData(context, routes)) {

        try {
            firestore.collection("users").document("driver")
                .collection("data").document(auth.currentUser?.email.toString())
                .update(
                    "from1",
                    routes.from1,
                    "from2",
                    routes.from2,
                    "from3",
                    routes.from3,
                    "to1",
                    routes.to1,
                    "to2",
                    routes.to2,
                    "to3",
                    routes.to3
                )
                .addOnSuccessListener {
                    Toast.makeText(context, "Routes Saved", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            navController.navigate(DriverScreens.DashboardScreen.route)
        } catch (e: Exception) {
            Log.i("testMain", "${e.message}")
        }
    }

}


fun validateSignUpRouteData(context: Context, routeData: DriverRouteList): Boolean {

    var isValid = false
    when {

        routeData.from1 == "-" ->
            Toast.makeText(
                context,
                "From state can't be empty",
                Toast.LENGTH_SHORT
            ).show()


        routeData.to1 == "-" ->
            Toast.makeText(
                context,
                "From city can't be empty",
                Toast.LENGTH_SHORT
            ).show()

        routeData.from2 == "-" ->
            Toast.makeText(
                context,
                "From state can't be empty",
                Toast.LENGTH_SHORT
            ).show()


        routeData.to2 == "-" ->
            Toast.makeText(
                context,
                "From city can't be empty",
                Toast.LENGTH_SHORT
            ).show()

        routeData.from3 == "-" ->
            Toast.makeText(
                context,
                "From state can't be empty",
                Toast.LENGTH_SHORT
            ).show()


        routeData.to3 == "-"->
            Toast.makeText(
                context,
                "From city can't be empty",
                Toast.LENGTH_SHORT
            ).show()

        else -> isValid = true

    }

    return isValid
}