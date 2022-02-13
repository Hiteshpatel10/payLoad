package com.geekaid.payload.dealer.firebaseDao.screenDao

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun dealerDealAddDao(
    dealDetails: DealerDealAddModel,
    context: Context,
    navController: NavController
) {

    val firestore = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    val dealerDocRef = firestore.collection("users").document("dealer")
        .collection(auth.currentUser?.email.toString()).document("dealData").collection("deals")
        .document(dealDetails.dealId)

    val dealerDealsDocRef = firestore.collection("allDeals").document(dealDetails.dealId)

    if (dealerDealAddValidateData(dealDetails = dealDetails, context = context)) {
        firestore.runBatch { runBatch ->
            runBatch.set(dealerDocRef, dealDetails)
            runBatch.set(dealerDealsDocRef, dealDetails)
        }

        Toast.makeText(context, "Deal Added Successfully", Toast.LENGTH_SHORT).show()

        navController.navigate(DealerScreens.DashboardScreen.route)
    }

}

fun dealerDealAddValidateData(
    dealDetails: DealerDealAddModel,
    context: Context,
): Boolean {

    when {

        dealDetails.natureOfMaterial.isEmpty() -> Toast.makeText(
            context,
            "Nature of material can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        dealDetails.weightOfMaterial.isEmpty() -> Toast.makeText(
            context,
            "Weight of material can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        dealDetails.quantity.isEmpty() -> Toast.makeText(
            context,
            "Quantity can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        dealDetails.price.isEmpty() -> Toast.makeText(
            context,
            "Price can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        dealDetails.from.isEmpty() -> Toast.makeText(
            context,
            "From State and city can't be empty",
            Toast.LENGTH_SHORT
        ).show()

        dealDetails.to.isEmpty() -> Toast.makeText(
            context,
            "From State and city can't be empty",
            Toast.LENGTH_SHORT
        ).show()


        else -> return true
    }
    return false
}