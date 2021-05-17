package com.sgssb.instacard.models

data class ExchangeModel(
    val id: String = "",
    val name: String = "",
    val flag: String = "",
    val buy: Double = 0.0,
    val sell: Double = 0.0,
    val symbol:String = "",
    val code:String = "",
)