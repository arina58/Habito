package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.BEST_STREAK
import com.example.habitstracker.CURRENT_STREAK
import javax.inject.Inject

class GetStreakUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(): Array<Int> {
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        val currentStreak = pref.getInt(CURRENT_STREAK, 0)
        val bestStreak = pref.getInt(BEST_STREAK, 0)

        return arrayOf(currentStreak, bestStreak)
    }
}