package com.geekaid.payload.dealer.ui.mainScreens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DealerDashboardScreen(navController: NavController) {

    val auth = FirebaseAuth.getInstance()

    Log.i("textMain", auth.currentUser?.email.toString())
    Log.i("textMain","fkgjaksgfhaksjgfs")

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(floatingActionButton = {

            FloatingActionButton(onClick = {
                navController.navigate(DealerScreens.DealerDealAddScreen.route)
            }, content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "image",
                    tint = Color.White
                )
            }, backgroundColor = MaterialTheme.colors.primaryVariant)
        }) {
            Text(text = "Add Deal")
        }
    }
}
