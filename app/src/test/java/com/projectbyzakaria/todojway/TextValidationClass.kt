package com.projectbyzakaria.todojway

import com.projectbyzakaria.todojway.utils.FormValidation
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TextValidationClass {

    @Test
    fun `text is email valid`(){
        val email = "zakaria@gmail.com"
        val email2 = "zakaria1321@gmail.com"
        val email3 = "zakarsss@sdsdfsd.sdfsd"
        val isEmailValid = FormValidation.isEmailValid(email)
        val isEmailValid2 = FormValidation.isEmailValid(email2)
        val isEmailValid3 = FormValidation.isEmailValid(email3)
        assertTrue(isEmailValid)
        assertTrue(isEmailValid2)
        assertTrue(isEmailValid3)
    }




    @Test
    fun `text is email not valid don't have '@'`(){
        // not have `@`
        val email = "zakariagmail.com"
        val isEmailValid = FormValidation.isEmailValid(email)
        assertFalse(isEmailValid)
    }


    @Test
    fun `text is email not valid haven't com`(){
        val email = "zakaria@gmail"
        val isEmailValid = FormValidation.isEmailValid(email)
        assertFalse(isEmailValid)
    }



    @Test
    fun `text is password valid`(){
        val password = "sfsdfs2df@A"
        val password2 = "sdfsd2fsd&Af"
        val password3 = "sdfsdfsdA222f#"
        val isPasswordValid = FormValidation.isPasswordValid(password)
        val isPasswordValid2 = FormValidation.isPasswordValid(password2)
        val isPasswordValid3 = FormValidation.isPasswordValid(password3)
        assertTrue(isPasswordValid)
        assertTrue(isPasswordValid2)
        assertTrue(isPasswordValid3)
    }




    @Test
    fun `text is password not valid`(){

        val password = "321321"
        val isPasswordValid = FormValidation.isPasswordValid(password)
        assertFalse(isPasswordValid)
    }


    @Test
    fun `text is email not valid haven't 2123123`(){
        val password = "gfhfghfgh"
        val isPasswordValid = FormValidation.isPasswordValid(password)
        assertFalse(isPasswordValid)
    }
}