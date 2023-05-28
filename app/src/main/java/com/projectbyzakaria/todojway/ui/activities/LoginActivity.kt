package com.projectbyzakaria.todojway.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projectbyzakaria.todojway.ui.screens.auth.FirstScreen
import com.projectbyzakaria.todojway.ui.screens.auth.LoginScreen
import com.projectbyzakaria.todojway.ui.screens.auth.RegisterScreen
import com.projectbyzakaria.todojway.ui.theme.ToDoJwayTheme
import com.projectbyzakaria.todojway.ui.viewmodels.AuthViewModel
import com.projectbyzakaria.todojway.utils.LoginScreens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {


    val authViewModel by viewModels<AuthViewModel>()
    private var isRegisterSuccess = false
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoJwayTheme {
                val navController = rememberNavController();
                val windowClass = calculateWindowSizeClass(this)
                val colorForFirstScreen = MaterialTheme.colorScheme.primary.toArgb()
                val defaultColor = MaterialTheme.colorScheme.onPrimary.toArgb()
                navController.addOnDestinationChangedListener{_,d,_->
                    if (d.route == LoginScreens.FIRST.route){
                        window.navigationBarColor = colorForFirstScreen
                    }else{
                        window.navigationBarColor = defaultColor
                    }
                }
                NavHost(
                    navController = navController,
                    startDestination = LoginScreens.FIRST.route
                ) {
                    composable(LoginScreens.FIRST.route) {
                        FirstScreen(
                            modifier = Modifier.fillMaxSize(),
                            onClickLogin = {
                                navController.navigate(LoginScreens.LOGIN.route)
                            }, onClickSignUn = {
                                navController.navigate(LoginScreens.REGISTER.route)
                            },windowClass
                        )
                    }

                    composable(LoginScreens.LOGIN.route){
                        LoginScreen(Modifier.fillMaxSize(),authViewModel,{navController.popBackStack()},windowClass, onClickLoginButton = ::login , onClickSignUp = {
                            navController.navigate(LoginScreens.REGISTER.route){
                                this.launchSingleTop = true
                                popUpTo(LoginScreens.FIRST.route)
                            }
                        },onSuccessLogIn=::onSuccessLogIn)
                    }
                    composable(LoginScreens.REGISTER.route){
                        RegisterScreen(Modifier.fillMaxSize(),authViewModel,{navController.popBackStack()},windowClass, onClickSignUpButton = ::register, onClickLogin = {
                            navController.navigate(LoginScreens.LOGIN.route){
                                this.launchSingleTop = true
                                popUpTo(LoginScreens.FIRST.route)
                            }
                        }, onSuccessSignUp = {
                            if (!isRegisterSuccess) {
                                Toast.makeText(this@LoginActivity, "success signUp please login now", Toast.LENGTH_SHORT).show()
                                navController.navigate(LoginScreens.LOGIN.route)
                                isRegisterSuccess = true
                            }
                        })
                    }
                }
            }
        }
    }


    private fun login(){
        authViewModel.login()
    }
    private fun register(){
        authViewModel.register()
    }


    private fun onSuccessLogIn(){
        authViewModel.onSuccessLogIn {
            val startToHome = Intent(this,MainActivity::class.java)
            startToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(startToHome)
        }
    }

}
