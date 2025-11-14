package com.example.miniproject2.data.remote

data class UserApiModel(
    val id: Int,
    val name: String,
    val username: String?,
    val email: String,
    val phone: String,
    val website: String?
)
