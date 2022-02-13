package com.geekaid.payload.driver.model

data class DriverSignUpModel(
    var email: String = "",
    var name: String = "",
    var age: String = "",
    var mobileNo: String = "",
    var truckNo: String = "",
    var truckCapacity: String = "",
    var transporterName: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var from1: String = "",
    var from2: String = "",
    var from3: String = "",
    var to1: String = "",
    var to2: String = "",
    var to3: String = "",
)
