package com.sgssb.instacard.models

data class TransactionModel(
    val id: String,
    val name: String,
    val amount: Double,
    val date: Long,
    val logo: String,
    val code: String,
    val type: String
)
