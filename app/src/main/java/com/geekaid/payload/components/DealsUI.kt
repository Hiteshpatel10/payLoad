package com.geekaid.payload.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geekaid.payload.dealer.model.DealerDealAddModel

@Composable
fun DealsObject(dealDetails: DealerDealAddModel) {

    Card(modifier = Modifier.padding(4.dp)) {

        Column() {

            HeadingValueStyle(heading = "Nature Of Material", value = dealDetails.natureOfMaterial, maxLines = true)
            HeadingValueStyle(heading = "Weight Of Material", value = dealDetails.weightOfMaterial, maxLines = true)
            HeadingValueStyle(heading = "Quantity", value = dealDetails.quantity, maxLines = true)
            HeadingValueStyle(heading = "Price", value = dealDetails.price, maxLines = true, isSpacer = true)

            HeadingValueStyle(heading = "From State", value = dealDetails.route.fromState, maxLines = true)
            HeadingValueStyle(heading = "From City", value = dealDetails.route.fromCity, maxLines = true)
            HeadingValueStyle(heading = "To City", value = dealDetails.route.toState, maxLines = true)
            HeadingValueStyle(heading = "To City", value = dealDetails.route.toCity, maxLines = true)

        }
    }

}