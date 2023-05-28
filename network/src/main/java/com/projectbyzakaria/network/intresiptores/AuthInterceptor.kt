package com.projectbyzakaria.network.intresiptores

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        return response
    }
}