package com.geekaid.payload.driver.ui.mainScreens

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
import com.geekaid.payload.components.DriverDealsUi
import com.geekaid.payload.components.NoDataFound
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.viewmodel.DriverViewModel

@ExperimentalMaterialApi
@Composable
fun DriverReqDealsScreen(driverViewModel: DriverViewModel, navController: NavController) {

    driverViewModel.getReqDealList().collectAsState(initial = null).value?.toObjects(
        DealerDealAddModel::class.java
    )?.let { deals ->
        driverViewModel.requestedDeals.value = deals
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        if (driverViewModel.requestedDeals.value.isEmpty()) {
            NoDataFound(displayText = "No Request Found")
        } else {
            LazyColumn {
                items(driverViewModel.requestedDeals.value) { dealDetails ->
                    DriverDealsUi(
                        dealDetails = dealDetails,
                        driverViewModel = driverViewModel,
                        navController = navController,
                        showText = true,
                        isReq = true
                    )
                }
            }
        }
    }

}