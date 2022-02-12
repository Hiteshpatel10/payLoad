package com.geekaid.payload.dealer.ui.mainScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.components.dropdownList
import com.geekaid.payload.dealer.firebaseDao.authDao.dealerDealAddDao
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.driver.model.DriverRoute
import com.geekaid.payload.driver.model.DriverRouteFromTo

@Composable
fun DealerDealAdd(navController: NavController) {

    var natureOfMaterial by remember { mutableStateOf("") }
    var weightOfMaterial by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    var from by remember { mutableStateOf(DriverRouteFromTo()) }
    var to by remember { mutableStateOf(DriverRouteFromTo()) }
    var route by remember { mutableStateOf(DriverRoute()) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = natureOfMaterial,
            onValueChange = { natureOfMaterial = it },
            label = { Text(text = "Nature Of Material") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = weightOfMaterial,
            onValueChange = { weightOfMaterial = it },
            label = { Text(text = "Weight Of Material") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text(text = "Quantity Of Material") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text(text = "Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Text(text = "Ship From")
        from = dropdownList(stateLabel = "From State", cityLabel = "From City", validateInput = false)

        Text(text = "Ship To")
        to = dropdownList(stateLabel = "To State", cityLabel = "To City", validateInput = false)

        route = DriverRoute(
            fromState = from.state,
            fromCity = from.city,
            toState = to.state,
            toCity = to.city
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Button(onClick = {
            dealerDealAddDao(
                dealDetails = DealerDealAddModel(
                    dealId = System.currentTimeMillis().toString(),
                    natureOfMaterial = natureOfMaterial,
                    weightOfMaterial = weightOfMaterial,
                    quantity = quantity,
                    price = price
                ),
                context = context,
                navController = navController
            )
        }) {
            Text(text = "Add Deal")
        }

    }
}