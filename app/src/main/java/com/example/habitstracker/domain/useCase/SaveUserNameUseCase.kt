package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN

class SaveUserNameUseCase {

    fun execute(Username: String){
        var pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        var editor = pref?.edit()
        editor?.putString("username", Username)
        editor?.commit()
    }
}