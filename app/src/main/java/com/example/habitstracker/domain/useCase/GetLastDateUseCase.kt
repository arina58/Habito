package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.CURRENT_DATE
import com.example.habitstracker.MAIN
import java.util.Calendar

class GetLastDateUseCase {
    fun execute(): Int{
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getInt(CURRENT_DATE, Calendar.DAY_OF_MONTH)
    }
}