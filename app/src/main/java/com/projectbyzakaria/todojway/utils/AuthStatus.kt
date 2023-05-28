package com.projectbyzakaria.todojway.utils

import com.projectbyzakaria.model.remote.auth.AuthResult

sealed class AuthStatus {
    object Loading : AuthStatus()
    data class Error(val message:String?,val errors:List<String>):AuthStatus()
    data class Success(val authData:AuthResult):AuthStatus()
    object Nating:AuthStatus()
}