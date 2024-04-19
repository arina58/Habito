package com.example.habitstracker.domain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.habitstracker.STATUS
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.SaveStreakUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import java.util.*

class MidnightProgress: BroadcastReceiver() {

    override fun onReceive(context: Context, Intent: Intent) {
        checkStreak(context)
        updateHabits(context)
        checkSunday(context)
    }

    private fun checkStreak(context: Context) {
        if(GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"), context).size == 0)
            SaveStreakUseCase().execute(true, context)
        else
            SaveStreakUseCase().execute(false, context)
    }

    private fun updateHabits(context: Context) {
        val habits = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"), context)
        habits.forEach {
            it.period -= 1
            it.current += 1
            it.status = 0
            if(it.current > it.best) it.best = it.current
            UpdateHabitUseCase().execute(it, context)
        }
    }

    private fun checkSunday(context: Context) {
        val cal = Calendar.getInstance()
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            val habits = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"), context)
            habits.forEach {
                it.date_of_week = 0
                UpdateHabitUseCase().execute(it, context)
            }
        }
    }
}
