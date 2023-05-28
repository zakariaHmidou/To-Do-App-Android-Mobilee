package com.projectbyzakaria.database.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.projectbyzakaria.database.auth.AuthUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.reflect.KProperty


@Module
@InstallIn(SingletonComponent::class)
object Model {


    private val Context.dataStore by preferencesDataStore("file_user")
    @Provides
    @Singleton
    fun provideAuthUser(@ApplicationContext context:Context):AuthUser {
        return AuthUser(context.dataStore)
    }




}