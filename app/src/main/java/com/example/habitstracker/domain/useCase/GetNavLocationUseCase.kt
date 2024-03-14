package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.*

class GetNavLocationUseCase {
    fun execute(): Boolean {
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getBoolean(NAV_LOCATION, DEFAULT_LOCATION)
    }
}