package com.projectbyzakaria.network.apis

import com.projectbyzakaria.model.remote.auth.AuthResult
import com.projectbyzakaria.model.remote.auth.User
import com.projectbyzakaria.network.BuildConfig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {




    @POST("login")
    suspend fun loginUser(@Body user:User):Response<AuthResult>

    @POST("register")
    suspend fun register(@Body user:User):Response<AuthResult>





}