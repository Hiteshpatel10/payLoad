package com.geekaid.payload.dealer.model

import com.geekaid.payload.driver.model.DriverRoute

data class DealerDealAddModel(
    var dealId: String = "",
    var natureOfMaterial: String = "",
    var weightOfMaterial: String = "",
    var quantity: String = "",
    var price: String = "",
    var assigned: Boolean = false,
    var route: DriverRoute = DriverRoute()

)
