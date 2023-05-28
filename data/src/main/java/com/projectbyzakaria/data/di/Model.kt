package com.projectbyzakaria.data.di

import com.projectbyzakaria.data.auth_repository.AuthUserRepository
import com.projectbyzakaria.data.auth_repository.AuthUserRepositoryImpl
import com.projectbyzakaria.data.home_repository.HomeRepository
import com.projectbyzakaria.data.home_repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface Model {


    @Binds
    fun provideAuthUserRepository(
        authUserRepositoryImpl: AuthUserRepositoryImpl
    ):AuthUserRepository



    @Binds
    fun provideHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ):HomeRepository


}