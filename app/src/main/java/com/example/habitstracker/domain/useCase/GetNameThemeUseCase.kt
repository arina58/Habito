package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.DEFAULT_NAME
import com.example.habitstracker.MAIN
import com.example.habitstracker.NAME_THEME
import com.example.habitstracker.USER_NAME

class GetNameThemeUseCase {

    fun execute(): String? {
        var pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getString(NAME_THEME, "DARK_THEME")
    }

}