package com.projectbyzakaria.todojway.ui.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectbyzakaria.data.auth_repository.AuthUserRepository
import com.projectbyzakaria.data.auth_repository.AuthUserRepositoryImpl
import com.projectbyzakaria.model.remote.auth.User
import com.projectbyzakaria.todojway.utils.AuthStatus
import com.projectbyzakaria.todojway.utils.FormValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthUserRepository
) : ViewModel() {

    val logInState = LoginState()
    val signUpState = SignUpState()

    class LoginState {
        var email by mutableStateOf("")
            private set


        fun changeEmail(newEmail: String) {
            if (isEmailHaveError) {
                isEmailHaveError = false
            }
            if (FormValidation.isEmailValid(newEmail.trim())) {
                isEmailCorrect = true
            } else {
                if (isEmailCorrect) {
                    isEmailCorrect = false
                }
            }
            if (newEmail.trim().isNotEmpty() && password.trim().isNotEmpty()) {
                if (!loginIsEnable) {
                    loginIsEnable = true
                }
            } else {
                loginIsEnable = false
            }
            this.email = newEmail.trim()
        }

        var password by mutableStateOf("")
            private set


        fun changePassword(newPassword: String) {
            if (isPasswordHaveError) {
                isPasswordHaveError = false
            }
            if (FormValidation.isPasswordValid(password.trim())) {
                isPasswordCorrect = true
            } else {
                if (isPasswordCorrect) {
                    isPasswordCorrect = false
                }
            }
            if (newPassword.trim().isNotEmpty() && email.trim().isNotEmpty()) {
                if (!loginIsEnable) {
                    loginIsEnable = true
                }
            } else {
                loginIsEnable = false
            }
            this.password = newPassword
        }

        var isEmailHaveError by mutableStateOf(false)
            private set

        var isEmailCorrect by mutableStateOf(false)
            private set


        var loginIsEnable by mutableStateOf(false)
            private set


        fun throwErrorForEmail(isErrorHapened: Boolean) {
            isEmailHaveError = isErrorHapened
        }

        fun throwErrorForPassword(isErrorHapened: Boolean) {
            isPasswordHaveError = isErrorHapened
        }

        var isPasswordHaveError by mutableStateOf(false)
            private set

        var isPasswordCorrect by mutableStateOf(false)
            private set


        var isPasswordVisible by mutableStateOf(false)
            private set

        fun changeVisibilttyOfPassword(isVisible: Boolean) {
            isPasswordVisible = isVisible
        }

    }
    class SignUpState {
        var email by mutableStateOf("")
            private set


        fun changeEmail(newEmail: String) {
            if (isEmailHaveError) {
                isEmailHaveError = false
            }
            if (FormValidation.isEmailValid(newEmail.trim())) {
                isEmailCorrect = true
            } else {
                if (isEmailCorrect) {
                    isEmailCorrect = false
                }
            }
            if (newEmail.trim().isNotEmpty() && password.trim().isNotEmpty()) {
                if (!loginIsEnable) {
                    loginIsEnable = true
                }
            } else {
                loginIsEnable = false
            }
            this.email = newEmail.trim()
        }

        var password by mutableStateOf("")
            private set


        fun changePassword(newPassword: String) {
            if (isPasswordHaveError) {
                isPasswordHaveError = false
            }
            if (FormValidation.isPasswordValid(password.trim())) {
                isPasswordCorrect = true
            } else {
                if (isPasswordCorrect) {
                    isPasswordCorrect = false
                }
            }
            if (newPassword.trim().isNotEmpty() && email.trim().isNotEmpty()) {
                if (!loginIsEnable) {
                    loginIsEnable = true
                }
            } else {
                loginIsEnable = false
            }
            this.password = newPassword
        }

        var isEmailHaveError by mutableStateOf(false)
            private set

        var isEmailCorrect by mutableStateOf(false)
            private set


        var loginIsEnable by mutableStateOf(false)
            private set


        fun throwErrorForEmail(isErrorHapened: Boolean) {
            isEmailHaveError = isErrorHapened
        }

        fun throwErrorForPassword(isErrorHapened: Boolean) {
            isPasswordHaveError = isErrorHapened
        }

        var isPasswordHaveError by mutableStateOf(false)
            private set

        var isPasswordCorrect by mutableStateOf(false)
            private set


        var isPasswordVisible by mutableStateOf(false)
            private set

        fun changeVisibilttyOfPassword(isVisible: Boolean) {
            isPasswordVisible = isVisible
        }

        var name by mutableStateOf("")
            private set
        var isNameCorrect by mutableStateOf(false)
            private set

        fun changeName(newName: String) {
            if (newName.isNotEmpty() && FormValidation.isNameValid(newName)) {
                if (!isNameCorrect) {
                    isNameCorrect = true
                }
            } else {
                if (isNameCorrect) {
                    isNameCorrect = false
                }
            }
            name = newName
        }

        var isNameHaveError by mutableStateOf(false)
            private set

        fun throwErrorForName(isErrorHapened: Boolean) {
            isNameHaveError = isErrorHapened
        }
    }


    private val _loginState = MutableStateFlow<AuthStatus>(AuthStatus.Nating)
    val loginStateUser
        get() = _loginState.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AuthStatus.Nating
        )


    private val _registerState = MutableStateFlow<AuthStatus>(AuthStatus.Nating)
    val registerStateUser
        get() = _registerState.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AuthStatus.Nating
        )

    fun login(){
        viewModelScope.launch {
            _loginState.emit(AuthStatus.Loading)
            val user = User(
                email = logInState.email,
                password = logInState.password
            )
            val loginResponse = repository.login(user)
            loginResponse.collectLatest {
                Log.d("ssssssssss", "login: $it")
                if (it.user?.id != null && it.token != null){
                    _loginState.emit(AuthStatus.Success(it))
                }else {
                    if (it.errors.isNullOrEmpty()){
                        _loginState.emit(AuthStatus.Error(it.message, emptyList()))
                    }else{
                        val errors = it.errors?.filterNotNull() ?: emptyList<String>()
                        _loginState.emit(AuthStatus.Error(it.message,errors))
                    }
                }
            }
        }
    }




    @SuppressLint("SuspiciousIndentation")
    fun register(){
        viewModelScope.launch {
        _registerState.emit(AuthStatus.Loading)
        val user = User(
            email = signUpState.email,
            password = signUpState.password,
            name = signUpState.name
        )
        val registerResponse = repository.register(user)
            registerResponse.collectLatest {
                Log.d("ssssssssss", "register: $it")
                if (it.user?.id != null && it.token != null){
                    _registerState.emit(AuthStatus.Success(it))
                }else {
                    if (it.errors.isNullOrEmpty()){
                        _registerState.emit(AuthStatus.Error(it.message, emptyList()))
                    }else{
                        val errors = it.errors?.filterNotNull() ?: emptyList<String>()
                        _registerState.emit(AuthStatus.Error(it.message,errors))
                    }
                }
            }
        }
    }




    fun markLoginScreenStateToNating(){
        _loginState.value = AuthStatus.Nating
    }
    fun markSignUpScreenStateToNating(){
        _registerState.value = AuthStatus.Nating
    }




    fun onSuccessLogIn(onEditSuccess:()->Unit){
        viewModelScope.launch {

            if (_loginState.value is AuthStatus.Success){
                val data = (_loginState.value as AuthStatus.Success).authData
                repository.auth().editUser(
                    user = data.user ?: User(),
                    token = data.token ?: ""
                )
                withContext(Dispatchers.Main){
                    onEditSuccess()
                }
            }else{
                _loginState.emit(AuthStatus.Error("please try login again ", emptyList()))
            }
        }
    }

}