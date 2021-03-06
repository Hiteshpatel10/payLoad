package com.geekaid.payload.dealer.ui.authScreens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.components.passwordVisible
import com.geekaid.payload.dealer.firebaseDao.authDao.dealerSignInDao
import com.geekaid.payload.dealer.model.DealerSigInModel
import com.geekaid.payload.dealer.navigation.DealerScreens

@Composable
fun DealerSignInScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val context: Context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(top = 80.dp)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Dealer SignIn", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Image(imageVector = Icons.Filled.Email, contentDescription = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            trailingIcon = {
                passwordVisibility = passwordVisible()
            },
            leadingIcon = {
                Image(
                    imageVector = Icons.Filled.Password,
                    contentDescription = "Email"
                )
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = {
                dealerSignInDao(
                    dealerSignInModel = DealerSigInModel(email, password),
                    context = context,
                    navController = navController
                )
            },
            contentPadding = PaddingValues(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        {
            Text(text = "Log In")
        }

        Button(
            onClick = {
                navController.navigate("driverOrDealer")
            },
            contentPadding = PaddingValues(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        {
            Text(text = "Change Dealer/Driver")
        }

        Row(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Forgotten your login details?", modifier = Modifier.alpha(0.7f))
            ClickableText(
                text = AnnotatedString(" Get help Signing in"),
                onClick = {
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(text = "Don't have an account?", modifier = Modifier.alpha(0.7f))
                ClickableText(
                    text = AnnotatedString(" Sign Up"),
                    onClick = {
                        navController.navigate(DealerScreens.SignUp.route)
                    }
                )
            }
        }
    }

}