package com.geekaid.payload.dealer.ui.mainScreens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.geekaid.payload.components.DealsUi
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.geekaid.payload.viewmodel.DealerViewModel
import com.google.firebase.auth.FirebaseAuth

@ExperimentalMaterialApi
@Composable
fun DealerDashboardScreen(dealerViewModel: DealerViewModel, navController: NavController) {

    dealerViewModel.getMyDeals()
        .collectAsState(initial = null).value?.toObjects(DealerDealAddModel::class.java)
        ?.let { deals ->
            dealerViewModel.allDeals.value = deals
        }

    Log.i("textMain", dealerViewModel.allDeals.toString())

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

            LazyColumn {

                items(dealerViewModel.allDeals.value){ deals->
                    DealsUi(dealDetails = deals, dealerViewModel = dealerViewModel, navController = navController)
                }
            }
        }
    }
}
