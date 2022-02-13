package com.geekaid.payload.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.driver.model.DriverDataModel
import com.geekaid.payload.repository.Repository
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DealerViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var allDeals: MutableState<List<DealerDealAddModel>> = mutableStateOf(listOf())
    var drivers: MutableState<List<DriverDataModel>> = mutableStateOf(listOf())
    var assignDriverDetails: MutableState<DriverDataModel> = mutableStateOf(DriverDataModel())


    var dealData: MutableState<DealerDealAddModel> = mutableStateOf(DealerDealAddModel())
    var assignFilter: MutableState<Boolean> = mutableStateOf(false)

    //to get all my deals
    fun getMyDeals() = repository.getMyDealsDealer(isAssigned = assignFilter.value)

    //to get the driverList
    fun getDriversList() = repository.findDriver(route = dealData.value.from)

    //to get the driver details
    fun getDriversDetails(email: String): Flow<DocumentSnapshot?> {
        return repository.getDriverDetails(email = email)
    }


}