package com.geekaid.payload.dealer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DealerScreens(val route: String, val title: String, val icon: ImageVector) {

    //Authentication Screens
    object SignIn: DealerScreens(route = "dealerSignIn", title = "SignIn", icon = Icons.Filled.Login)
    object SignUp: DealerScreens(route = "dealerSignUp",title = "SignUp", icon = Icons.Filled.Logout)
    object SignOut: DealerScreens(route = "dealerSignOut",title = "SignOut", icon = Icons.Filled.Logout)

    //Main Screens
    object DashboardScreen: DealerScreens(route = "dealerDashboard",title = "Dashboard", icon = Icons.Filled.Dashboard)
    object DealerDealAddScreen: DealerScreens(route = "dealerDealAddScreen",title = "Add Deal", icon = Icons.Filled.Add)
    object DealerDriverListScreen: DealerScreens(route = "dealerDriverListScreen",title = "Drivers List", icon = Icons.Filled.List)
    object DealerDriverAssignScreen: DealerScreens(route = "dealerDriverAssignScreen",title = "Driver", icon = Icons.Filled.List)
}
