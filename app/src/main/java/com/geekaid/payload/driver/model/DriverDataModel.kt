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
)
