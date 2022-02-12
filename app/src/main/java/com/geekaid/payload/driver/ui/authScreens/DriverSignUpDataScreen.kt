package com.geekaid.payload.driver.ui.authScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geekaid.payload.components.dropdownList
import com.geekaid.payload.driver.model.DriverRoute
import com.geekaid.payload.util.StateList
import com.geekaid.payload.util.stateCityList

@Composable
fun DriverSignUpDataScreen() {

    var truckNumber by remember { mutableStateOf("") }
    var truckCapacity by remember { mutableStateOf("") }
    var transporterName by remember { mutableStateOf("") }
    var route1 by remember { mutableStateOf(DriverRoute()) }
    var route2 by remember { mutableStateOf(DriverRoute()) }
    var route3 by remember { mutableStateOf(DriverRoute()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        OutlinedTextField(
            value = truckNumber,
            onValueChange = { truckNumber = it },
            label = { Text(text = "Mobile Number") },
            leadingIcon = {
                Icon(Icons.Filled.ConfirmationNumber, contentDescription = "Email")
            },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = truckCapacity,
            onValueChange = { truckCapacity = it },
            label = { Text(text = "Mobile Number") },
            leadingIcon = {
                Icon(Icons.Filled.ConfirmationNumber, contentDescription = "Email")
            },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = transporterName,
            onValueChange = { transporterName = it },
            label = { Text(text = "Mobile Number") },
            leadingIcon = {
                Icon(Icons.Filled.ConfirmationNumber, contentDescription = "Email")
            },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Row() {
            route1.FromState =
                dropdownList(list = StateList.stateList, label = "test", validateInput = false)
            if (route1.FromState.isNotEmpty())
                dropdownList(list = stateCityList(state = route1.FromState), label = "test", validateInput = false)
        }


    }

}