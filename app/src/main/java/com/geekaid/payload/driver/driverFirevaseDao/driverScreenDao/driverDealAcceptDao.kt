package com.geekaid.payload.driver.driverFirevaseDao.driverScreenDao

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun driverDealAcceptDao(dealerDealAddModel: DealerDealAddModel, context: Context) {

    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore

    val reqDocRef = firestore.collection("users").document("driver")
        .collection("jobs").document(auth.currentUser?.email.toString())
        .collection("jobReq").document(dealerDealAddModel.dealId)

    val assignDocRef = firestore.collection("users").document("driver")
        .collection("jobs").document(auth.currentUser?.email.toString())
        .collection("jobAssign").document(dealerDealAddModel.dealId)

    val dealerDocRef = firestore.collection("users").document("dealer")
        .collection(dealerDealAddModel.dealerEmail).document("dealData").collection("deals")
        .document(dealerDealAddModel.dealId)

    try {
        dealerDocRef.get()
            .addOnSuccessListener { document ->

                if (document.exists()) {
                    val doc = document.toObject(DealerDealAddModel::class.java)

                    if (doc?.assigned == true) {
                        Toast.makeText(
                            context,
                            "Already assigned to someone else",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        dealerDealAddModel.assigned = true
                        dealerDealAddModel.assignedToEmail = auth.currentUser?.email.toString()
                        firestore.runBatch { runBatch ->
                            runBatch.update(
                                dealerDocRef,
                                "assigned",
                                true,
                                "assignedToEmail",
                                auth.currentUser?.email.toString()
                            )
                            runBatch.delete(reqDocRef)
                            runBatch.set(assignDocRef, dealerDealAddModel)
                        }

                    }
                }
            }
    } catch (e: Exception) {
        Log.i("testDeal", e.message.toString())
    }


}