package com.projectbyzakaria.model.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResult(
    var token: String? = null,
    var user: User? = null,
    var status: Int? = null,
    var message: String? = null,
    var errors: List<String?>? = null
)