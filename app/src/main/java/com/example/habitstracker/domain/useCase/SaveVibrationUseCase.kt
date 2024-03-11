package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN
import com.example.habitstracker.VIBRATION

class SaveVibrationUseCase {
    fun execute(Value: Boolean){
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putBoolean(VIBRATION, Value)
        editor?.apply()
    }
}