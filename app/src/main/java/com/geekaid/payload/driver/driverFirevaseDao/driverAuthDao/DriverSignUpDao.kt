package com.geekaid.payload.driver.driverFirevaseDao.driverAuthDao

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.geekaid.payload.driver.model.DriverDataModel
import com.geekaid.payload.driver.model.DriverSignUpModel
import com.geekaid.payload.driver.navigation.DriverScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


fun driverSignUpDao(
    credentials: DriverSignUpModel,
    context: Context,
    navController: NavController
) {

    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore

    if (validateSignUpData(context, credentials)) {
        auth.createUserWithEmailAndPassword(credentials.email, credentials.password)
            .addOnSuccessListener {
                Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()

                firestore.collection("users").document("driver")
                    .collection(credentials.email).document("data").set(
                    DriverDataModel(
                        email = credentials.email,
                        name = credentials.name,
                        mobileNo = credentials.mobileNo,
                        age = credentials.age,
                        truckNo = credentials.truckNo,
                        truckCapacity = credentials.truckCapacity,
                        transporterName = credentials.transporterName
                    )
                )
                navController.navigate(DriverScreens.SignUpData.route)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

}

fun validateSignUpData(context: Context, credential: DriverSignUpModel): Boolean {

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


        credential.age.isEmpty() -> Toast.makeText(
            context,
            "Age can't be empty",
            Toast.LENGTH_SHORT
        ).show()


        credential.truckNo.isEmpty() -> Toast.makeText(
            context,
            "Truck Number can't be empty",
            Toast.LENGTH_SHORT
        ).show()


        credential.truckCapacity.isEmpty() -> Toast.makeText(
            context,
            "Truck capacity can't be empty",
            Toast.LENGTH_SHORT
        ).show()


        credential.transporterName.isEmpty() -> Toast.makeText(
            context,
            "Transporter name can't be empty",
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


