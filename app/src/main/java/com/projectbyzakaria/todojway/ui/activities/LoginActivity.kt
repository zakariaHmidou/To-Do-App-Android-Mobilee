package com.projectbyzakaria.todojway.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.projectbyzakaria.todojway.ui.theme.ToDoJwayTheme
import com.projectbyzakaria.todojway.utils.LoginScreens

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoJwayTheme {
                val navController = rememberNavController();
                NavHost(navController = navController, startDestination = LoginScreens.LOGIN.route){

                }
            }
        }
    }
}
