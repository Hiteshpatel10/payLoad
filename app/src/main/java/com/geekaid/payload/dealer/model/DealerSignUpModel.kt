package com.geekaid.payload.dealer.model

data class DealerSignUpModel(
    var email: String = "",
    var name: String = "",
    var mobileNo: String = "",
    var password: String = "",
    var confirmPassword: String = ""
)
