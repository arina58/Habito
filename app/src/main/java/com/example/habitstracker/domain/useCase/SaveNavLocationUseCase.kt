package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN
import com.example.habitstracker.NAV_LOCATION

class SaveNavLocationUseCase {
    fun execute(location: Boolean){
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putBoolean(NAV_LOCATION, location) //true is settings
        editor?.apply()
    }
}