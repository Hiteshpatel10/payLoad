package com.geekaid.payload.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    //variables
    var requestedDeals: MutableState<List<DealerDealAddModel>> = mutableStateOf(listOf())
    var assignDeals: MutableState<List<DealerDealAddModel>> = mutableStateOf(listOf())
    var allDeals: MutableState<List<DealerDealAddModel>> = mutableStateOf(listOf())

    var delaData: MutableState<DealerDealAddModel> = mutableStateOf(DealerDealAddModel())
    var assignFilter: MutableState<Boolean> = mutableStateOf(false)


    //To get the request deals list
    fun getReqDealList() = repository.getReqDealsList()

    fun getAssignDealList() = repository.getAssignDealsList(isCompleted = assignFilter.value)

    fun getAllDealList() = repository.getAllDeals()

}