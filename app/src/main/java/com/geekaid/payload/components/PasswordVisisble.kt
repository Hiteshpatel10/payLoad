package com.geekaid.payload.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun passwordVisible(): Boolean{
    var passwordVisibility by remember { mutableStateOf(false) }

    IconButton(onClick = {
        passwordVisibility = !passwordVisibility
    }) {
        Icon(
            Icons.Filled.RemoveRedEye,
            contentDescription = null,
            tint = if (passwordVisibility) MaterialTheme.colors.primary else Color.Gray
        )
    }

    return passwordVisibility
}