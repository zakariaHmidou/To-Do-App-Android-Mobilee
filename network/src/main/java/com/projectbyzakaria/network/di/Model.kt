package com.projectbyzakaria.network.di

import com.google.gson.GsonBuilder
import com.projectbyzakaria.network.BuildConfig
import com.projectbyzakaria.network.apis.AuthApi
import com.projectbyzakaria.network.intresiptores.AuthInterceptor
import dagger.Module

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Model {


    @Provides
    @Singleton
    fun provideRetrofit():Retrofit {

        val client = OkHttpClient.Builder()
            .writeTimeout(20L,TimeUnit.SECONDS)
            .readTimeout(20L,TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit):AuthApi{
        return retrofit.create(AuthApi::class.java)
    }



}