package com.geekaid.payload.dealer.firebaseDao.authDao

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.geekaid.payload.dealer.model.DealerSigInModel
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.google.firebase.auth.FirebaseAuth

fun dealerSignInDao(dealerSignInModel: DealerSigInModel, context: Context, navController: NavController){

    val auth = FirebaseAuth.getInstance()

    if (validateSignInData(context, dealerSignInModel)) {

        auth.signInWithEmailAndPassword(dealerSignInModel.email, dealerSignInModel.password)
            .addOnFailureListener {
                Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "SignIn Successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(DealerScreens.DashboardScreen.route) {
                        navController.popBackStack()
                    }
                }
            }
    }
}

fun validateSignInData(context: Context, credential: DealerSigInModel): Boolean {

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
