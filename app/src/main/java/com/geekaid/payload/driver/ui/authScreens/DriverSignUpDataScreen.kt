package com.geekaid.payload.driver.ui.authScreens

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
import com.geekaid.payload.driver.model.DriverRoute
import com.geekaid.payload.driver.model.DriverRouteFromTo
import com.geekaid.payload.driver.model.DriverRouteList

@Composable
fun DriverSignUpDataScreen(navController: NavController) {

    var route1 by remember { mutableStateOf(DriverRoute()) }
    var route2 by remember { mutableStateOf(DriverRoute()) }
    var route3 by remember { mutableStateOf(DriverRoute()) }
    var from by remember { mutableStateOf(DriverRouteFromTo()) }
    var to by remember { mutableStateOf(DriverRouteFromTo()) }

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
        from =
            dropdownList(stateLabel = "From State", cityLabel = "From City", validateInput = false)
        to = dropdownList(stateLabel = "To State", cityLabel = "To City", validateInput = false)
        route1 = DriverRoute(
            fromState = from.state,
            fromCity = from.city,
            toState = to.state,
            toCity = to.city
        )


        Spacer(modifier = Modifier.padding(4.dp))

        Text(text = "Route2")
        from =
            dropdownList(stateLabel = "From State", cityLabel = "From City", validateInput = false)
        to = dropdownList(stateLabel = "To State", cityLabel = "To City", validateInput = false)
        route2 = DriverRoute(
            fromState = from.state,
            fromCity = from.city,
            toState = to.state,
            toCity = to.city
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(text = "Route3")
        from =
            dropdownList(stateLabel = "From State", cityLabel = "From City", validateInput = false)
        to = dropdownList(stateLabel = "To State", cityLabel = "To City", validateInput = false)
        route3 = DriverRoute(
            fromState = from.state,
            fromCity = from.city,
            toState = to.state,
            toCity = to.city
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Button(onClick = {
            driverSignUpDataDao(
                credentials = DriverRouteList(
                    routeList = listOf(
                        route1,
                        route2,
                        route3
                    )
                ), context = context, navController = navController
            )
        }) {
            Text(text = "Sign Up")
        }

    }

}