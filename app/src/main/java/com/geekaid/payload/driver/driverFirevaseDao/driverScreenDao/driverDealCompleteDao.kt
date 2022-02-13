package com.geekaid.payload.driver.driverFirevaseDao.driverScreenDao

import android.content.Context
import android.widget.Toast
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun driverDealCompleteDao(dealerDealAddModel: DealerDealAddModel, context: Context) {

    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore


    val assignDocRef = firestore.collection("users").document("driver")
        .collection("jobs").document(auth.currentUser?.email.toString())
        .collection("jobAssign").document(dealerDealAddModel.dealId)

    firestore.runBatch { runBatch ->
        runBatch.update(assignDocRef, "completed", true)
    }


}