package com.sgssb.instacard.models

data class StockModel(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val share: Double = 0.0,
    val fallen: Boolean = false,
    val code: String = "",
    val percentage: Double = 0.0

)
