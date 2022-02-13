package com.geekaid.payload.driver.model

import android.provider.ContactsContract

data class DriverDataModel(
    var email: String = "",
    var name: String = "",
    var age: String = "",
    var mobileNo: String = "",
    var truckNo: String = "",
    var truckCapacity: String = "",
    var transporterName: String = "",
    var from1: String = "",
    var from2: String = "",
    var from3: String = "",
    var to1: String = "",
    var to2: String = "",
    var to3: String = "",
)
