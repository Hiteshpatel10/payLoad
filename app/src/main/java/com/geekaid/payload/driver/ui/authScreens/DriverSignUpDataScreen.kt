package com.geekaid.payload.ui.authScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.components.dropdownList
import com.geekaid.payload.driver.driverFirevaseDao.driverAuthDao.driverSignUpDataDao
import com.geekaid.payload.driver.model.DriverRouteList

@Composable
fun DriverSignUpDataScreen(navController: NavController) {

    var from1 by remember { mutableStateOf("") }
    var from2 by remember { mutableStateOf("") }
    var from3 by remember { mutableStateOf("") }
    var to1 by remember { mutableStateOf("") }
    var to2 by remember { mutableStateOf("") }
    var to3 by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "Enter Your 3 preferred route", textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.padding(4.dp))

        Text(text = "Route1")
        from1 =
            dropdownList(stateLabel = "From State", cityLabel = "From City", validateInput = false)
        to1 = dropdownList(stateLabel = "To State", cityLabel = "To City", validateInput = false)

        Spacer(modifier = Modifier.padding(4.dp))

        Text(text = "Route2")
        from2 =
            dropdownList(stateLabel = "From State", cityLabel = "From City", validateInput = false)
        to2 = dropdownList(stateLabel = "To State", cityLabel = "To City", validateInput = false)

        Spacer(modifier = Modifier.padding(4.dp))

        Text(text = "Route3")
        from3 =
            dropdownList(stateLabel = "From State", cityLabel = "From City", validateInput = false)
        to3 = dropdownList(stateLabel = "To State", cityLabel = "To City", validateInput = false)

        Spacer(modifier = Modifier.padding(4.dp))

        Button(onClick = {
            driverSignUpDataDao(
                routes = DriverRouteList(
                    from1 = from1,
                    from2 = from2,
                    from3 = from3,
                    to1 = to1,
                    to2 = to2,
                    to3 = to3
                ), context = context, navController = navController
            )
        }) {
            Text(text = "Sign Up")
        }

    }

}