package com.geekaid.payload.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.driver.driverFirevaseDao.driverScreenDao.driverDealAcceptDao
import com.geekaid.payload.driver.driverFirevaseDao.driverScreenDao.driverDealCompleteDao
import com.geekaid.payload.viewmodel.DriverViewModel

@ExperimentalMaterialApi
@Composable
fun DriverDealsUi(
    dealDetails: DealerDealAddModel,
    driverViewModel: DriverViewModel,
    navController: NavController,
    showText: Boolean,
    isReq: Boolean
) {

    var isExpanded by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    val text = if (isReq) "Click here to accept" else "Click here to mark complete"
    val context = LocalContext.current

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(), onClick = { isExpanded = !isExpanded }) {

        Column(modifier = Modifier.padding(4.dp)) {

            if (showText && !dealDetails.completed)
                ClickableText(
                    text = AnnotatedString(text = text),
                    onClick = {
                        openDialog = true
                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 80.dp)
                        .fillMaxWidth()
                )

            if (isExpanded) {
                HeadingValueStyle(heading = "Date", value = dealDetails.dealDate, maxLines = true)
                HeadingValueStyle(
                    heading = "Nature Of Material",
                    value = dealDetails.natureOfMaterial,
                    maxLines = true
                )
                HeadingValueStyle(
                    heading = "Weight Of Material",
                    value = dealDetails.weightOfMaterial,
                    maxLines = true
                )
                HeadingValueStyle(
                    heading = "Quantity",
                    value = dealDetails.quantity,
                    maxLines = true
                )
                HeadingValueStyle(
                    heading = "Price",
                    value = dealDetails.price,
                    maxLines = true,
                    isSpacer = true
                )

                HeadingValueStyle(
                    heading = "From",
                    value = dealDetails.from,
                    maxLines = true
                )
                HeadingValueStyle(
                    heading = "To",
                    value = dealDetails.to,
                    maxLines = true
                )

            } else {
                HeadingValueStyle(heading = "Date", value = dealDetails.dealDate, maxLines = true)
                HeadingValueStyle(
                    heading = "Nature Of Material",
                    value = dealDetails.natureOfMaterial,
                    maxLines = true
                )
                HeadingValueStyle(
                    heading = "Weight Of Material",
                    value = dealDetails.weightOfMaterial,
                    maxLines = true
                )
                HeadingValueStyle(
                    heading = "Quantity",
                    value = dealDetails.quantity,
                    maxLines = true
                )
                HeadingValueStyle(
                    heading = "Price",
                    value = dealDetails.price,
                    maxLines = true,
                    isSpacer = true
                )
            }


        }
    }

    //Request ones
    if (openDialog && isReq)
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
                        driverDealAcceptDao(dealDetails, context = context)
                    }, modifier = Modifier.padding(12.dp)) {
                        Text(text = "Accept")
                    }

                    Button(onClick = { openDialog = false }, modifier = Modifier.padding(12.dp)) {
                        Text(text = "cancel")
                    }
                }
            })

    //to mark assign complete
    if (openDialog && !isReq)
        AlertDialog(
            onDismissRequest = { openDialog = false },

            text = {
                Text(
                    text = "Mark as complete",
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
                        driverDealCompleteDao(dealerDealAddModel = dealDetails, context = context)
                        Log.i("testAlert","clicked")
                    }, modifier = Modifier.padding(12.dp)) {
                        Text(text = "Complete")
                    }

                    Button(onClick = { openDialog = false }, modifier = Modifier.padding(12.dp)) {
                        Text(text = "cancel")
                    }
                }
            })

}