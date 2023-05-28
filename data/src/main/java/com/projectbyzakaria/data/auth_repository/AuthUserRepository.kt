package com.projectbyzakaria.data.auth_repository

import com.projectbyzakaria.database.auth.AuthUser
import com.projectbyzakaria.model.remote.auth.AuthResult
import com.projectbyzakaria.model.remote.auth.User
import kotlinx.coroutines.flow.Flow


interface AuthUserRepository {

    fun login(user: User):Flow<AuthResult>

    fun register(user: User):Flow<AuthResult>

    fun auth(): AuthUser

}