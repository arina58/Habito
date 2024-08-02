package com.example.habitstracker.domain.useCase

import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

class SwitchThemeUseCase @Inject constructor() {

    operator fun invoke(nameTheme: Boolean){
        if (nameTheme) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}