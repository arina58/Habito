package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.*
import javax.inject.Inject

class SaveStreakUseCase @Inject constructor(
    private val context: Context
) {

    operator fun invoke(flag: Boolean){
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        var currentStreak = pref.getInt(CURRENT_STREAK, 0)
        val editor = pref?.edit()
        if(flag){
            currentStreak += 1
            editor?.putInt(CURRENT_STREAK, currentStreak)
            editor?.apply()
        }else{
            editor?.putInt(CURRENT_STREAK, 0)
            editor?.apply()
        }
        val bestStreak = pref.getInt(BEST_STREAK, 0)
        if(currentStreak > bestStreak){
            editor?.putInt(BEST_STREAK, currentStreak)
            editor?.apply()
        }
    }

}