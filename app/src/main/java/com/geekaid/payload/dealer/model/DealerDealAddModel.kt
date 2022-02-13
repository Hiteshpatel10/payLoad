package com.geekaid.payload.dealer.model


data class DealerDealAddModel(
    var dealId: String = "",
    var dealerEmail: String = "",
    var natureOfMaterial: String = "",
    var weightOfMaterial: String = "",
    var quantity: String = "",
    var price: String = "",
    var assigned: Boolean = false,
    var assignedToEmail: String = "",
    var completed: Boolean = false,
    var dealDate: String = "",
    var from: String = "",
    var to: String = "",

)
