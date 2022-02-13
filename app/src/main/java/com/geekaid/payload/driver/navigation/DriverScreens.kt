package com.geekaid.payload.driver.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class DriverScreens(val route: String, val title: String, val icon: ImageVector) {

    //Authentication Screens
    object SignIn :
        DriverScreens(route = "driverSignIn", title = "SignIn", icon = Icons.Filled.Login)

    object SignUp :
        DriverScreens(route = "driverSignUp", title = "SignUp", icon = Icons.Filled.Logout)

    object SignUpData :
        DriverScreens(route = "driverSignUpData", title = "User Details", icon = Icons.Filled.Login)

    //Main Screens
    object DashboardScreen :
        DriverScreens(route = "driverDashboard", title = "All Deals", icon = Icons.Filled.Dashboard)

    object AssignDealsScreen :
        DriverScreens(route = "assignDealsScreen", title = "Assign Deals", icon = Icons.Filled.Assignment)

    object ReqDealsScreen :
        DriverScreens(route = "reqDealsScreen", title = "Req Deals", icon = Icons.Filled.RequestPage)


    object SignOutScreen :
        DriverScreens(route = "signOut", title = "Sign Out", icon = Icons.Filled.Logout)
}
