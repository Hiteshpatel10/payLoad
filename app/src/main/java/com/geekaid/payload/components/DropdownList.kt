package com.geekaid.payload.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.geekaid.payload.driver.model.DriverRouteFromTo
import com.geekaid.payload.driver.ui.authScreens.DriverSignUpDataScreen
import com.geekaid.payload.util.StateList
import com.geekaid.payload.util.stateCityList

@Composable
fun dropdownList(
    stateLabel: String,
    cityLabel: String,
    defaultValue: String = "",
    validateInput: Boolean
): DriverRouteFromTo {
    var cityExpanded by remember { mutableStateOf(false) }
    var stateExpanded by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(defaultValue) }
    var city by remember { mutableStateOf(defaultValue) }
    var cityList by remember { mutableStateOf(listOf<String>()) }
    var stateList by remember { mutableStateOf(StateList.stateList) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var isError by remember { mutableStateOf(false) }

    if (validateInput && state.isEmpty() && city.isNotEmpty())
        isError = true

    val stateIcon = if (stateExpanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    val cityIcon = if (cityExpanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown



    Column(
        modifier = Modifier
            .padding(bottom = 2.dp, top = 2.dp)
    ) {
        //State

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 4.dp)
        ) {
            OutlinedTextField(
                value = state,
                onValueChange = {
                    stateList.forEach { item ->
                        if (item == it)
                            state = it
                    }
                },
                label = { Text(stateLabel) },
                trailingIcon = {
                    Icon(stateIcon, "contentDescription",
                        Modifier.clickable { stateExpanded = !stateExpanded})
                },
                isError = isError,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    }
                    .onFocusChanged {
                        if (it.isFocused) {
                            stateExpanded = true
                        }
                    }
            )
            DropdownMenu(
                expanded = stateExpanded,
                onDismissRequest = { stateExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
                stateList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        state = label
                        stateExpanded = false
                        city = ""
                    }) {
                        Text(text = label)
                    }
                }
            }

            Spacer(modifier = Modifier.padding(4.dp))


            if (state.isNotEmpty()) {
                //City
                cityList = stateCityList(state = state)
                OutlinedTextField(
                    value = city,
                    onValueChange = {
                        cityList.forEach { item ->
                            if (item == it)
                                city = it
                        }
                    },
                    label = { Text(cityLabel) },
                    trailingIcon = {
                        Icon(cityIcon, "contentDescription",
                            Modifier.clickable { cityExpanded= !cityExpanded })
                    },
                    isError = isError,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        }
                        .onFocusChanged {
                            if (it.isFocused) {
                                cityExpanded = true
                            }
                        }
                )
                DropdownMenu(
                    expanded = cityExpanded,
                    onDismissRequest = { cityExpanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                ) {
                    cityList.forEach { label ->
                        DropdownMenuItem(onClick = {
                            city = label
                            cityExpanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }

        }

        if (isError) {
            Text(
                text = "Fields can't be empty",
                color = Color.Red,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    return DriverRouteFromTo(state = state, city = city)
}
