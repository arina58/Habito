package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.CURRENT_DATE
import com.example.habitstracker.MAIN

class SaveCurrentDateUseCase {
    fun execute(date: Int){
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putInt(CURRENT_DATE, date)
        editor?.apply()
    }
}