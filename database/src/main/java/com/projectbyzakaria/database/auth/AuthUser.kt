package com.projectbyzakaria.database.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.projectbyzakaria.model.remote.auth.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthUser(
    private val dataStore: DataStore<Preferences>,
) {

    private val emailKey = stringPreferencesKey("email")
    private val tokenKey = stringPreferencesKey("token")
    private val idKey = intPreferencesKey("id")
    private val nameKey = stringPreferencesKey("name")
    private val coroutine = CoroutineScope(Dispatchers.Main)
    private var onFinishGetDataUser: OnFinishGetDataUser? = null
    var isUserLogged: Boolean = false
    var idUser: Int = 0
    var token: String = ""
    var email: String = ""
    var name: String = ""


    suspend fun editUser(user: User, token: String) {
        dataStore.edit {
            it[emailKey] = user.email ?: ""
            it[tokenKey] = token
            it[idKey] = user.id ?: -1
            it[nameKey] = user.name ?: ""
        }
    }

    init {
        readUserInfo()
    }

    private fun readUserInfo() {
        coroutine.launch {
            dataStore.data.collectLatest { it ->
                email = it[emailKey] ?: ""
                idUser = it[idKey] ?: -1
                token = it[tokenKey] ?: ""
                name = it[nameKey] ?: ""
                isUserLogged = idUser != -1 && token.isNotEmpty()
                onFinishGetDataUser?.let { it1->
                    it1.userData(
                        email = email,
                        id = idUser,
                        token = token,
                        name=  name,
                        isUserLogged = isUserLogged
                    )
                }
            }
            coroutine.cancel()
        }
    }

    fun refresh() {
        readUserInfo()
    }

    fun setOnFinishGetDataUser(onFinishGetDataUser: OnFinishGetDataUser) {
        this.onFinishGetDataUser = onFinishGetDataUser
    }



}