package com.geekaid.payload.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun HeadingValueStyle(
    heading: String,
    value: String,
    isSpacer: Boolean = false,
    maxLines: Boolean = false
) {

    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif
                )
            ) {
                append("$heading : ")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal
                )
            ) {
                append(value)
            }
        },
        maxLines = if (maxLines) 1 else 5,
        modifier = Modifier.padding(bottom = 2.dp)
    )

    if (isSpacer)
        Spacer(modifier = Modifier.padding(4.dp))

}
