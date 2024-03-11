package com.example.habitstracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.habitstracker.domain.useCase.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MidnightTaskReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        CoroutineScope(Dispatchers.IO).launch {
            SaveCurrentStreakUseCase().execute(CheckUncompletedHabitsUseCase().execute())
            SetHabitsUncompleteUseCase().execute()
            setMinusOneDay()
        }
    }

    private fun setMinusOneDay(){
        var habits = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"))
        habits.forEach {
            it.period -= 1
            UpdateHabitUseCase().execute(it)
        }
    }
}