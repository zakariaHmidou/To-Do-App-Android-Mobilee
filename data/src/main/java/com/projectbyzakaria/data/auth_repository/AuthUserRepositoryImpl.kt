package com.projectbyzakaria.data.auth_repository

import com.projectbyzakaria.database.auth.AuthUser
import com.projectbyzakaria.model.remote.auth.User
import com.projectbyzakaria.network.repositories.AuthRepository
import javax.inject.Inject


class AuthUserRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val authUser: AuthUser,
) : AuthUserRepository   {

    override fun login(user: User) = authRepository.loginUser(user)

    override fun register(user: User) = authRepository.register(user)

    override fun auth() = authUser



}