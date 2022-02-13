package com.geekaid.payload.dealer.ui.mainScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.components.DriverDetailUi
import com.geekaid.payload.components.NoDataFound
import com.geekaid.payload.driver.model.DriverDataModel
import com.geekaid.payload.viewmodel.DealerViewModel

@ExperimentalMaterialApi
@Composable
fun DealerDriverListScreen(dealerViewModel: DealerViewModel, navController: NavController) {

    dealerViewModel.getDriversList()
        .collectAsState(initial = null).value?.toObjects(DriverDataModel::class.java)
        ?.let { driverList ->
            dealerViewModel.drivers.value = driverList
        }

    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize()) {

       if (dealerViewModel.drivers.value.isEmpty()){
           NoDataFound(displayText = "No drivers available for this route")
           
       }else{
           LazyColumn {
               items(dealerViewModel.drivers.value) { driverDetails ->
                   DriverDetailUi(driverDetails = driverDetails, dealDataModel = dealerViewModel.dealData.value)
               }
           }
       }
    }
}