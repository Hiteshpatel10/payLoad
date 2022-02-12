package com.geekaid.payload.driver.navigation

sealed class DriverScreens(val route: String){

    //Authentication Screens
    object SignIn: DriverScreens("driverSignIn")
    object SignUp: DriverScreens("driverSignUp")
    object SignUpData: DriverScreens("driverSignUpData")

    //Main Screens
    object DashboardScreen: DriverScreens("driverDashboard")

}
