package com.projectbyzakaria.todojway.utils

object FormValidation {


    fun isEmailValid(email:String):Boolean{
        val pattern = Regex("[a-zA-Z]{5,}[0-9]*@[a-z]+\\.[a-z]+")
        return email.matches(pattern)
    }

    fun isPasswordValid(password: String): Boolean {
        val pattern =Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*()]{8,}$")

        return password.matches(pattern)
    }

    fun isNameValid(name: String): Boolean {
        val pattern =Regex("^[a-zA-Z]{3,}$")
        return name.matches(pattern)
    }
}