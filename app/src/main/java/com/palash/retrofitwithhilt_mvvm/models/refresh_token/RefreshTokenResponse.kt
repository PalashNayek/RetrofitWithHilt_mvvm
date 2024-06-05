package com.palash.retrofitwithhilt_mvvm.models.refresh_token

data class RefreshTokenResponse(
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val token: String,
    val username: String
)