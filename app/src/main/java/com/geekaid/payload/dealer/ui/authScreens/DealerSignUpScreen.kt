package com.geekaid.payload.dealer.ui.authScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.components.passwordVisible
import com.geekaid.payload.dealer.firebaseDao.authDao.dealerSignUpDao
import com.geekaid.payload.dealer.model.DealerSignUpModel
import com.geekaid.payload.dealer.navigation.DealerScreens

@Composable
fun DealerSignUpScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(top = 80.dp)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(Icons.Filled.Email, contentDescription = "Email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name") },
            leadingIcon = {
                Icon(Icons.Filled.Person, contentDescription = "Email")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = mobileNumber,
            onValueChange = { mobileNumber = it },
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
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Filled.Password, contentDescription = "password") },
            trailingIcon = { passwordVisibility = passwordVisible() },
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            leadingIcon = { Icon(Icons.Filled.Password, contentDescription = "password") },
            trailingIcon = { confirmPasswordVisibility = passwordVisible() },
            visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )


        Button(
            onClick = {
                dealerSignUpDao(
                    credential = DealerSignUpModel(
                        email = email,
                        name = name,
                        mobileNo = mobileNumber,
                        password = password,
                        confirmPassword = password
                    ),
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
            Text(text = "Sign Up")
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
                Text(text = "Already have an account?", modifier = Modifier.alpha(0.7f))
                ClickableText(
                    text = AnnotatedString(" Log In"),
                    onClick = {
                        navController.navigate(DealerScreens.SignIn.route)
                    }
                )
            }
        }
    }
}