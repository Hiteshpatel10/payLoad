package com.geekaid.payload.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

@Composable
fun dropdownList(
    list: List<String>,
    label: String,
    defaultValue: String = "",
    validateInput: Boolean
): String {
    var expanded by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(defaultValue) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var isError by remember { mutableStateOf(false) }

    if (validateInput && state.isEmpty())
        isError = true

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    Column(
        modifier = Modifier
            .padding(bottom = 2.dp, top = 2.dp)
    ) {

        OutlinedTextField(
            value = state,
            onValueChange = {
                list.forEach { item ->
                    if (item == it)
                        state = it
                }
            },
            label = { Text(label) },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },
            isError = isError,
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .onFocusChanged {
                    if (it.isFocused) {
                        expanded = true
                    }
                }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(onClick = {
                    state = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }


            if (isError) {
                Text(
                    text = "$label can't be empty",
                    color = Color.Red,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
    return state
}
