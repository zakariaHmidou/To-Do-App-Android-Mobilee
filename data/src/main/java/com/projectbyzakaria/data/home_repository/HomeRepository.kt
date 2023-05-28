package com.projectbyzakaria.data.home_repository

import com.projectbyzakaria.database.auth.AuthUser

interface HomeRepository {

    fun auth():AuthUser

}