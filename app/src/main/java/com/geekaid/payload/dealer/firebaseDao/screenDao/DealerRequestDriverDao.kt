package com.geekaid.payload.dealer.firebaseDao.screenDao

import android.content.Context
import android.widget.Toast
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.driver.model.DriverDataModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun dealerRequestDriverDao(
    dealerDealAddModel: DealerDealAddModel,
    driverDataModel: DriverDataModel,
    context: Context
) {

    val firestore = Firebase.firestore

    val docRef = firestore.collection("users").document("driver")
        .collection("jobs").document(driverDataModel.email)
        .collection("jobReq").document(dealerDealAddModel.dealId)


    docRef.set(dealerDealAddModel)
        .addOnSuccessListener {
            Toast.makeText(context, "Deal Requested", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener {
            Toast.makeText(context, "Error : ${it.message}", Toast.LENGTH_SHORT).show()
        }


}