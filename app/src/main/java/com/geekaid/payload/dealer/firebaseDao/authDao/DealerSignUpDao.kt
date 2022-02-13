package com.geekaid.payload.dealer.firebaseDao.authDao

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.geekaid.payload.dealer.model.DealerDataModel
import com.geekaid.payload.dealer.model.DealerSignUpModel
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun dealerSignUpDao(
    credential: DealerSignUpModel,
    context: Context,
    navController: NavController
) {

    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore

    if (validateSignUpData(context, credential)) {
        auth.createUserWithEmailAndPassword(credential.email, credential.password)
            .addOnSuccessListener {
                Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()
                firestore.collection("users").document("dealer")
                    .collection(credential.email).document("data").set(
                    DealerDataModel(
                        name = credential.name,
                        mobileNumber = credential.mobileNo
                    )
                )
                navController.navigate(DealerScreens.DashboardScreen.route)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

}

fun validateSignUpData(context: Context, credential: DealerSignUpModel): Boolean {

    when {
        credential.email.isEmpty() -> Toast.makeText(
            context,
            "Email can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        credential.name.isEmpty() -> Toast.makeText(
            context,
            "Name can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        credential.mobileNo.isEmpty() -> Toast.makeText(
            context,
            "Mobile Number can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        credential.mobileNo.length != 10 -> Toast.makeText(
            context,
            "Enter a valid mobile no",
            Toast.LENGTH_SHORT
        ).show()

        credential.password.isEmpty() -> Toast.makeText(
            context,
            "Password can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        credential.confirmPassword.isEmpty() -> Toast.makeText(
            context,
            "Confirm Password can't be empty",
            Toast.LENGTH_SHORT
        ).show()


        credential.password.isNotEmpty() -> {
            if (credential.password != credential.confirmPassword)
                Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show()
            else
                return true
        }
    }
    return false
}
