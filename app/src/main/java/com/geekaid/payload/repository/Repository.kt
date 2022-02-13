package com.geekaid.payload.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Singleton

@Singleton
class Repository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun getMyDealsDealer() = callbackFlow {

        val dealerDealsDocRef = firestore.collection("users").document("dealer")
            .collection(auth.currentUser?.email.toString()).document("dealData").collection("deals")
            .orderBy("dealDate", Query.Direction.DESCENDING)

        val snapshotListener = dealerDealsDocRef.addSnapshotListener { value, error ->
            if (error == null)
                trySend(value)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

    fun findDriver(route: String) = callbackFlow {

        val driverList = firestore.collection("users").document("driver")
            .collection("data").whereEqualTo("from1", route)

        val snapshotListener = driverList.addSnapshotListener { value, error ->
            if (error == null)
                trySend(value)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

}