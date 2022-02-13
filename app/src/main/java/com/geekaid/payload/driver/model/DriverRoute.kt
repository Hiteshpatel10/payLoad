package com.geekaid.payload.driver.model

data class DriverRoute(
    var fromState: String = "",
    var fromCity: String = "",
    var toState: String = "",
    var toCity: String = "",
)

data class DriverRouteList(
    var from1: String = "",
    var from2: String = "",
    var from3: String = "",
    var to1: String = "",
    var to2: String = "",
    var to3: String = "",
)

