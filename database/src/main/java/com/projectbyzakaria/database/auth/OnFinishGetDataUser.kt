package com.projectbyzakaria.database.auth

interface OnFinishGetDataUser {
    fun userData(email:String,id:Int,token:String,name:String,isUserLogged:Boolean)
}