package com.geekaid.payload.driver.ui.mainScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.components.DriverDealsUi
import com.geekaid.payload.components.NoDataFound
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.viewmodel.DriverViewModel

@ExperimentalMaterialApi
@Composable
fun DriverAssignDealsScreen(driverViewModel: DriverViewModel, navController: NavController) {

    driverViewModel.getAssignDealList().collectAsState(initial = null).value?.toObjects(
        DealerDealAddModel::class.java
    )?.let { deals ->
        driverViewModel.assignDeals.value = deals
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(Modifier.padding(4.dp)) {
            Row(Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {

                Button(onClick = {
                    driverViewModel.assignFilter.value = false
                }, Modifier.padding(vertical = 2.dp, horizontal = 8.dp)) {
                    Text(text = "OnGoing Jobs")
                }

                Button(onClick = {
                    driverViewModel.assignFilter.value = true
                }, Modifier.padding(vertical = 2.dp, horizontal = 8.dp)) {
                    Text(text = "Completed Jobs")
                }
            }
        }

        if (driverViewModel.assignDeals.value.isEmpty()) {
            NoDataFound(displayText = "No Assign Deals Now")
        } else {
            LazyColumn {
                items(driverViewModel.assignDeals.value) { dealDetails ->
                    DriverDealsUi(
                        dealDetails = dealDetails,
                        driverViewModel = driverViewModel,
                        navController = navController,
                        showText = true,
                        isReq = false
                    )
                }
            }
        }
    }

}