package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN
import com.example.habitstracker.NAME_THEME

class SaveNameThemeUseCase {

    fun execute(NameTheme: String){
        var pref = MAIN.getSharedPreferences("Theme", Context.MODE_PRIVATE)
        var editor = pref?.edit()
        editor?.putString(NAME_THEME, NameTheme)
        editor?.commit()
    }

}