package com.example.habitstracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.habitstracker.domain.useCase.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class MidnightTaskReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        CoroutineScope(Dispatchers.IO).launch {
            SaveCurrentStreakUseCase().execute(CheckUncompletedHabitsUseCase().execute())
            setHabitsParams()
            SetHabitsUncompleteUseCase().execute()
            checkSunday()
        }
    }

    private fun setHabitsParams(){
        var habits = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"))
        habits.forEach {
            it.period -= 1
            it.current += 1
            if(it.current > it.best) it.best = it.current
            UpdateHabitUseCase().execute(it)
        }
    }

    private fun checkSunday(){
        val cal = Calendar.getInstance()
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            var habits = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0", "1"))
            habits.forEach {
                it.date_of_week = 0
                UpdateHabitUseCase().execute(it)
            }
        }
    }
}