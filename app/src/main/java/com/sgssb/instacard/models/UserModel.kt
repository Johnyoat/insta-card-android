package com.sgssb.instacard.models

data class UserModel(
    val uid: String = "",
    val name: String = "",
    val card: String = "",
    val dob: Long = 0,
    val amount: Double = 0.00,
    val profileUrl:String = ""
)
