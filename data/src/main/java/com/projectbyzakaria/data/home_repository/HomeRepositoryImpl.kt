package com.projectbyzakaria.data.home_repository

import com.projectbyzakaria.database.auth.AuthUser
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val authUser: AuthUser
) : HomeRepository {





    override fun auth() = authUser


}