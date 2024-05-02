package com.example.wspolicy.models

data class UserData(
    val data: UserInfo,
    val success: Boolean
)

data class UserInfo(
    val id: String,
    val login: String,
    val name: String,
    val token: String
)