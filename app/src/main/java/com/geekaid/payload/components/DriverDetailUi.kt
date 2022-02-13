package com.geekaid.payload.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.geekaid.payload.dealer.firebaseDao.screenDao.dealerRequestDriverDao
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.driver.model.DriverDataModel

@ExperimentalMaterialApi
@Composable
fun DriverDetailUi(driverDetails: DriverDataModel, dealDataModel: DealerDealAddModel) {

    var openDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = {
            openDialog = true
        }
    ) {

        Column(modifier = Modifier.padding(4.dp)) {

            HeadingValueStyle(heading = "Name", value = driverDetails.name, maxLines = true)
            HeadingValueStyle(heading = "Age", value = driverDetails.age, maxLines = true)
            HeadingValueStyle(
                heading = "Mobile Number",
                value = driverDetails.mobileNo,
                maxLines = true
            )
            HeadingValueStyle(
                heading = "Truck Number",
                value = driverDetails.truckNo,
                maxLines = true,
                isSpacer = true
            )
            HeadingValueStyle(
                heading = "Truck Capacity",
                value = driverDetails.truckCapacity,
                maxLines = true
            )
            HeadingValueStyle(
                heading = "Transporter Name",
                value = driverDetails.transporterName,
                maxLines = true,
                isSpacer = true
            )
            HeadingValueStyle(
                heading = "Price",
                value = driverDetails.truckNo,
                maxLines = true,
                isSpacer = true
            )


        }
    }

    if (openDialog)
        AlertDialog(
            onDismissRequest = { openDialog = false },

            text = {
                Text(
                    text = "Ask The Driver",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },

            buttons = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 16.dp, end = 4.dp)
                        .fillMaxWidth()
                ) {

                    Button(onClick = {
                        openDialog = false

                        dealerRequestDriverDao(
                            dealerDealAddModel = dealDataModel,
                            driverDataModel = driverDetails,
                            context = context
                        )

                    }, modifier = Modifier.padding(12.dp)) {
                        Text(text = "Request Assign")
                    }

                    Button(onClick = { openDialog = false }, modifier = Modifier.padding(12.dp)) {
                        Text(text = "cancel")
                    }
                }
            })

}