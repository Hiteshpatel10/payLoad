package com.geekaid.payload.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.geekaid.payload.viewmodel.DealerViewModel

@ExperimentalMaterialApi
@Composable
fun DealerDealsUi(
    dealDetails: DealerDealAddModel,
    dealerViewModel: DealerViewModel,
    navController: NavController
) {

    var isExpanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(), onClick = { isExpanded = !isExpanded }) {

        Column(modifier = Modifier.padding(4.dp)) {

            if (!dealDetails.assigned)
                ClickableText(
                    text = AnnotatedString("Click here to find driver"),
                    onClick = {
                        dealerViewModel.dealData.value = dealDetails
                        navController.navigate(DealerScreens.DealerDriverListScreen.route)
                    },
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 100.dp)
                        .fillMaxWidth()
                )

            if (dealDetails.assigned)
                ClickableText(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        ) {
                            append("Assigned To: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.Blue
                            )
                        ) {
                            append(dealDetails.assignedToEmail)
                        }
                    },
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 4.dp),
                    onClick = {
                        navController.navigate("${DealerScreens.DealerDriverAssignScreen.route}/${dealDetails.assignedToEmail}")
                    }
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


}