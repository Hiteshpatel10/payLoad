package com.geekaid.payload.dealer.ui.mainScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.geekaid.payload.components.HeadingValueStyle
import com.geekaid.payload.driver.model.DriverDataModel
import com.geekaid.payload.viewmodel.DealerViewModel

@Composable
fun DealerDriverAssignScreen(email: String?, dealerViewModel: DealerViewModel) {

    email?.let {
        dealerViewModel.getDriversDetails(email = it)
            .collectAsState(initial = null).value?.toObject(
            DriverDataModel::class.java
        )?.let { details ->
            dealerViewModel.assignDriverDetails.value = details
        }
    }

    Card(modifier = Modifier.padding(8.dp)) {

        Column(modifier = Modifier.padding(4.dp)) {

            HeadingValueStyle(
                heading = "Name",
                value = dealerViewModel.assignDriverDetails.value.name,
                maxLines = true,
                isSpacer = true
            )

            HeadingValueStyle(
                heading = "Age",
                value = dealerViewModel.assignDriverDetails.value.age,
                maxLines = true,
                isSpacer = true
            )

            HeadingValueStyle(
                heading = "Mobile Number",
                value = dealerViewModel.assignDriverDetails.value.mobileNo,
                maxLines = true,
                isSpacer = true
            )

            HeadingValueStyle(
                heading = "Truck Number",
                value = dealerViewModel.assignDriverDetails.value.truckNo,
                maxLines = true,
                isSpacer = true
            )

            HeadingValueStyle(
                heading = "Truck Capacity",
                value = dealerViewModel.assignDriverDetails.value.truckCapacity,
                maxLines = true,
                isSpacer = true
            )

            HeadingValueStyle(
                heading = "Transporter Name",
                value = dealerViewModel.assignDriverDetails.value.transporterName,
                maxLines = true,
                isSpacer = true
            )

            Text(text = "Preferred Locations", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(8.dp))

            HeadingValueStyle(
                heading = "From",
                value = dealerViewModel.assignDriverDetails.value.from1,
                maxLines = true,
            )

            HeadingValueStyle(
                heading = "To",
                value = dealerViewModel.assignDriverDetails.value.to1,
                maxLines = true,
                isSpacer = true
            )

            HeadingValueStyle(
                heading = "From",
                value = dealerViewModel.assignDriverDetails.value.from2,
                maxLines = true,
            )

            HeadingValueStyle(
                heading = "To",
                value = dealerViewModel.assignDriverDetails.value.to2,
                maxLines = true,
                isSpacer = true
            )

            HeadingValueStyle(
                heading = "From",
                value = dealerViewModel.assignDriverDetails.value.from2,
                maxLines = true,

            )

            HeadingValueStyle(
                heading = "To",
                value = dealerViewModel.assignDriverDetails.value.to2,
                maxLines = true,
                isSpacer = true
            )

        }
    }

}