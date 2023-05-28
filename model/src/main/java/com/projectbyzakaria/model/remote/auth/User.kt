package com.projectbyzakaria.model.remote.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class User(
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    val password: String? = null,
    var email_verified_at: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
)