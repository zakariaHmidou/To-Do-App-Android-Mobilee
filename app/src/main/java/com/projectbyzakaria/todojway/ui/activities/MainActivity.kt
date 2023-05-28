package com.projectbyzakaria.todojway.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.core.os.BuildCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.projectbyzakaria.database.auth.AuthUser
import com.projectbyzakaria.database.auth.OnFinishGetDataUser
import com.projectbyzakaria.todojway.ui.screens.auth.FirstScreen
import com.projectbyzakaria.todojway.ui.theme.ToDoJwayTheme
import com.projectbyzakaria.todojway.ui.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() ,OnFinishGetDataUser{

    val homeViewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ToDoJwayTheme {
                homeViewModel.auth().setOnFinishGetDataUser(this)
            }
        }
    }

    override fun userData(
        email: String,
        id: Int,
        token: String,
        name: String,
        isUserLogged: Boolean,
    ) {
        if (!isUserLogged){
            val startToHome = Intent(this,LoginActivity::class.java)
            startToHome.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(startToHome)
        }
        Toast.makeText(this, "$isUserLogged", Toast.LENGTH_SHORT).show()
    }
}
