package com.geekaid.payload.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Singleton

@Singleton
class Repository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun getMyDealsDealer(isAssigned: Boolean) = callbackFlow {

        val dealerDealsDocRef = firestore.collection("users").document("dealer")
            .collection(auth.currentUser?.email.toString()).document("dealData").collection("deals")
            .whereEqualTo("assigned", isAssigned)

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
                .collection("data")
                .whereEqualTo("from1",route)

            val snapshotListener = driverList.addSnapshotListener { value, error ->
                if (error == null)
                    trySend(value)
            }

            awaitClose {
                snapshotListener.remove()
            }

    }


    fun getReqDealsList() = callbackFlow {

        val driverList = firestore.collection("users").document("driver")
            .collection("jobs").document(auth.currentUser?.email.toString())
            .collection("jobReq")

        val snapshotListener = driverList.addSnapshotListener { value, error ->
            if (error == null)
                trySend(value)
        }

        awaitClose {
            snapshotListener.remove()
        }

    }

    fun getAssignDealsList(isCompleted: Boolean) = callbackFlow {

        val driverList = firestore.collection("users").document("driver")
            .collection("jobs").document(auth.currentUser?.email.toString())
            .collection("jobAssign").whereEqualTo("completed", isCompleted)

        val snapshotListener = driverList.addSnapshotListener { value, error ->
            if (error == null)
                trySend(value)
        }

        awaitClose {
            snapshotListener.remove()
        }

    }

    fun getAllDeals() = callbackFlow {

        val allDealRef = firestore.collection("allDeals")

        val snapshotListener = allDealRef.addSnapshotListener { value, error ->
            if (error == null)
                trySend(value)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

    fun getDriverDetails(email: String) = callbackFlow {

        val driverDetails = firestore.collection("users").document("driver")
            .collection("data").document(email)

        val snapshotListener = driverDetails.addSnapshotListener { value, error ->
            if (error == null)
                trySend(value)
        }

        awaitClose {
            snapshotListener.remove()
        }

    }



}