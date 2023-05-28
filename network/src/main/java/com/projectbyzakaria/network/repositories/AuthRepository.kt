package com.projectbyzakaria.network.repositories

import android.util.Log
import com.google.gson.Gson
import com.projectbyzakaria.model.remote.auth.AuthResult
import com.projectbyzakaria.model.remote.auth.User
import com.projectbyzakaria.network.apis.AuthApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

class  AuthRepository @Inject constructor(
    private val authApi:AuthApi
) {

     fun loginUser(user: User): Flow<AuthResult> = flow {
        try {
            val userResponse = authApi.loginUser(user)

            if (userResponse.isSuccessful){
                val errorResponse = AuthResult(
                    token = null,
                    user = null,
                    status = null,
                    message = null,
                    errors = null
                )
                val result = userResponse.body()?.let {
                    it
                } ?: errorResponse
                emit(result)

            }else{
                val messageBody = userResponse.errorBody()?.string()
                val parseToGson = Gson().fromJson(messageBody,AuthResult::class.java)
                emit(parseToGson)
            }

        }catch (error:IOException){
            val errorResponse = AuthResult(
                token = null,
                user = null,
                status = null,
                message = null,
                errors = listOf("Please make sure you have access to the Internet")
            )
            emit(errorResponse)
        }catch (error:Exception){
            val errorResponse = AuthResult(
                token = null,
                user = null,
                status = null,
                message = null,
                errors = listOf(error.message)
            )
            emit(errorResponse)
        }
    }


     fun register(user: User): Flow<AuthResult> = flow {
        try {
            val userResponse = authApi.register(user)
            if (userResponse.isSuccessful){
                val errorResponse = AuthResult(
                    token = null,
                    user = null,
                    status = null,
                    message = null,
                    errors = null
                )
                val result = userResponse.body()?.let {
                    it
                } ?: errorResponse
                emit(result)

            }else{
                val messageBody = userResponse.errorBody()?.string()
                val parseToGson = Gson().fromJson(messageBody,AuthResult::class.java)
                emit(parseToGson)
            }

        }catch (error:IOException){
            val errorResponse = AuthResult(
                token = null,
                user = null,
                status = null,
                message = null,
                errors = listOf("Please make sure you have access to the Internet")
            )
            emit(errorResponse)
        }catch (error:Exception){
            val errorResponse = AuthResult(
                token = null,
                user = null,
                status = null,
                message = null,
                errors = listOf(error.message)
            )
            emit(errorResponse)
        }
    }
}