package com.geekaid.payload.dealer.navigation

sealed class DealerScreens(val route: String){

    //Authentication Screens
    object SignIn: DealerScreens("dealerSignIn")
    object SignUp: DealerScreens("dealerSignUp")

    //Main Screens
    object DashboardScreen: DealerScreens("dealerDashboard")
    object DealerDealAddScreen: DealerScreens("dealerDealAddScreen")
}
