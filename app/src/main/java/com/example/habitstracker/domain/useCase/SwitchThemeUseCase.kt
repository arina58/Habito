package com.example.habitstracker.domain.useCase

import androidx.appcompat.app.AppCompatDelegate

class SwitchThemeUseCase {

    fun execute(NameTheme: Boolean){
        if (NameTheme) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}