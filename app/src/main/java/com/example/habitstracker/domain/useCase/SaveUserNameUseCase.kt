package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN

class SaveUserNameUseCase {

    fun execute(Username: String){
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putString("username", Username)
        editor?.apply()
    }
}