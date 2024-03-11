package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN
import com.example.habitstracker.SOUND

class SaveSoundUseCase {

    fun execute(Value: Float){
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putFloat(SOUND, Value)
        editor?.apply()
    }

}