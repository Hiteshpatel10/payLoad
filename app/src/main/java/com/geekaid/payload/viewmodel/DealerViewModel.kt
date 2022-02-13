package com.geekaid.payload.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.driver.model.DriverDataModel
import com.geekaid.payload.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DealerViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var allDeals: MutableState<List<DealerDealAddModel>> = mutableStateOf(listOf())
    var drivers: MutableState<List<DriverDataModel>> = mutableStateOf(listOf())


    var dealData: MutableState<DealerDealAddModel> = mutableStateOf(DealerDealAddModel())

    //to get all my deals
    fun getMyDeals() = repository.getMyDealsDealer()

    //to get the driverList
    fun getDriversList() = repository.findDriver(route = dealData.value.from)

}