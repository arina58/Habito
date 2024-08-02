package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.NAME_THEME
import javax.inject.Inject

class SaveNameThemeUseCase @Inject constructor() {

    operator fun invoke(context: Context, nameTheme: Boolean){
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putBoolean(NAME_THEME, nameTheme)
        editor?.apply()
    }
}