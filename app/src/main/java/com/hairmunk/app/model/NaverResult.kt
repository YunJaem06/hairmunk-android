package com.hairmunk.app.model

data class NaverResult(
    val resultCode: String,
    val message: String,
    val response: Profile
)

data class Profile(
    val id: String,
    val email: String,
    val name: String,
    val img : String
)
