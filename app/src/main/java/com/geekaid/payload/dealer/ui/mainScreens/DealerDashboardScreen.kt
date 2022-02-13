package com.geekaid.payload.dealer.ui.mainScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.geekaid.payload.components.DealerDealsUi
import com.geekaid.payload.dealer.model.DealerDealAddModel
import com.geekaid.payload.dealer.navigation.DealerScreens
import com.geekaid.payload.viewmodel.DealerViewModel

@ExperimentalMaterialApi
@Composable
fun DealerDashboardScreen(dealerViewModel: DealerViewModel, navController: NavController) {

    dealerViewModel.getMyDeals()
        .collectAsState(initial = null).value?.toObjects(DealerDealAddModel::class.java)
        ?.let { deals ->
            dealerViewModel.allDeals.value = deals
        }


    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate(DealerScreens.DealerDealAddScreen.route)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "image",
                        tint = Color.White
                    )
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(vertical = 50.dp, horizontal = 24.dp)
            )
        }) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, start = 8.dp, bottom = 80.dp, end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(Modifier.padding(4.dp)) {
                    Row(Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {

                        Button(
                            onClick = {
                                dealerViewModel.assignFilter.value = false
                            },
                            Modifier.padding(vertical = 2.dp, horizontal = 8.dp)
                        ) {
                            Text(text = "Not Assigned")
                        }

                        Button(onClick = {
                            dealerViewModel.assignFilter.value = true
                        }, Modifier.padding(vertical = 2.dp, horizontal = 8.dp)) {
                            Text(text = "Assigned")
                        }
                    }
                }

                LazyColumn {
                    items(dealerViewModel.allDeals.value) { deals ->
                        DealerDealsUi(
                            dealDetails = deals,
                            dealerViewModel = dealerViewModel,
                            navController = navController
                        )
                    }
                }
            }

        }
    }
}
