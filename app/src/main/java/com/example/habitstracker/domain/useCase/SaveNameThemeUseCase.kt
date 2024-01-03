package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN
import com.example.habitstracker.NAME_THEME

class SaveNameThemeUseCase {

    fun execute(NameTheme: Boolean){
        var pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        var editor = pref?.edit()
        editor?.putBoolean(NAME_THEME, NameTheme)
        editor?.commit()
    }

}