package com.geekaid.payload.driver.model

data class DriverRoute(
    var fromState: String = "",
    var fromCity: String = "",
    var toState: String = "",
    var toCity: String = "",
)

data class DriverRouteFromTo(
    var state: String = "",
    var city: String = "",
)

data class DriverRouteList(
    var routeList: List<DriverRoute> = listOf(DriverRoute())
)

