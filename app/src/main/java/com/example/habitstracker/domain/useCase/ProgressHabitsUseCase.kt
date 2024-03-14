package com.example.habitstracker.domain.useCase

import com.example.habitstracker.STATUS
import java.util.Calendar
class ProgressHabitsUseCase {
    fun execute(){
        checkStreak()

        var habits = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"))
        habits.forEach {
            it.period -= 1
            it.current += 1
            it.status = 0
            if(it.current > it.best) it.best = it.current
            UpdateHabitUseCase().execute(it)
        }

        checkSunday()
    }
    private fun checkStreak(){
        if(GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0")).size == 0) SaveCurrentStreakUseCase().execute(true)
        else SaveCurrentStreakUseCase().execute(false)
    }

    private fun checkSunday(){
        val cal = Calendar.getInstance()
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            var habits = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"))
            habits.forEach {
                it.date_of_week = 0
                UpdateHabitUseCase().execute(it)
            }
        }
    }
}