package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN
import com.example.habitstracker.NAME_THEME

class GetNameThemeUseCase {

    fun execute(): Boolean {
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getBoolean(NAME_THEME, false)
    }

}