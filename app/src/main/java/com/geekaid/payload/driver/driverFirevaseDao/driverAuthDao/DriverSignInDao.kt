package com.geekaid.payload.driver.driverFirevaseDao.driverAuthDao

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.geekaid.payload.driver.model.DriverSignInModel
import com.geekaid.payload.driver.navigation.DriverScreens
import com.google.firebase.auth.FirebaseAuth

fun driverSignInDao(credential: DriverSignInModel, context: Context, navController: NavController) {

    val auth = FirebaseAuth.getInstance()

    if (driverValidateSignInData(context, credential)) {

        auth.signInWithEmailAndPassword(credential.email, credential.password)
            .addOnFailureListener {
                Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "SignIn Successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(DriverScreens.DashboardScreen.route) {
                        navController.popBackStack()
                    }
                }
            }
    }

}

fun driverValidateSignInData(context: Context, credential: DriverSignInModel): Boolean {

    when {
        credential.email.isEmpty() -> Toast.makeText(
            context,
            "Email can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        credential.password.isEmpty() -> Toast.makeText(
            context,
            "Password can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        credential.email.isNotEmpty() -> {
            if (credential.password.isNotEmpty())
                return true
        }
    }

    return false
}