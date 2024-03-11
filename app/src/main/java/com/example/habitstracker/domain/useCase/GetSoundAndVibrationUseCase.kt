package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN
import com.example.habitstracker.SOUND
import com.example.habitstracker.VIBRATION

class GetSoundAndVibrationUseCase {
    val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
    fun getSound(): Float{
        return pref.getFloat(SOUND, 100.0F)
    }

    fun getVibration(): Boolean{
        return pref.getBoolean(VIBRATION, true)
    }

}