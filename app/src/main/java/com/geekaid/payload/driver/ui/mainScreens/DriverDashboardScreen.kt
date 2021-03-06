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
fun DriverDashboardScreen(driverViewModel: DriverViewModel, navController: NavController) {

    driverViewModel.getAllDealList().collectAsState(initial = null).value?.toObjects(
        DealerDealAddModel::class.java
    )?.let { deals ->
        driverViewModel.allDeals.value = deals
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        if (driverViewModel.allDeals.value.isEmpty()) {
            NoDataFound(displayText = "No Delas Available")
        } else {
            LazyColumn {
                items(driverViewModel.allDeals.value) { dealDetails ->
                    DriverDealsUi(
                        dealDetails = dealDetails,
                        driverViewModel = driverViewModel,
                        navController = navController,
                        showText = false,
                        isReq = false
                    )
                }
            }
        }
    }
}