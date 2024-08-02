package com.example.habitstracker.domain

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.habitstracker.STATUS
import com.example.habitstracker.data.HabitRepositoryImpl
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.SaveStreakUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class MidnightProgress @Inject constructor(
    private val saveStreakUseCase: SaveStreakUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val getHabitsFromDBUseCase: GetHabitsFromDBUseCase
) : BroadcastReceiver() {

    private val data = getHabitsFromDBUseCase.invoke().value


    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {
        checkStreak(context)
        updateHabits()
        checkSunday()
    }

    private fun checkStreak(context: Context) {
        var addStreakFlag = true
        data?.forEach {
            if (it.status == 0) addStreakFlag = false
        }
        if (addStreakFlag)
            saveStreakUseCase(true, context)
        else
            saveStreakUseCase(false, context)
    }

    private fun updateHabits() {
        data?.forEach {
            if (it.status == 1){
                it.period -= 1
                it.current += 1
                it.status = 0
                if (it.current > it.best) it.best = it.current
                scope.launch {
                    updateHabitUseCase(it)
                }
            }
        }
    }

    private fun checkSunday() {
        val cal = Calendar.getInstance()
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            data?.forEach {
                if(it.status == 0){
                    it.date_of_week = 0
                    scope.launch {
                        updateHabitUseCase(it)
                    }
                }
            }
        }
    }
}
