package com.example.habitstracker.domain.useCase

import com.example.habitstracker.STATUS

class SetHabitsUncompleteUseCase {
    fun execute(){
        var habits = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"))
        habits.forEach {
            it.status = 0
            UpdateHabitUseCase().execute(it)
        }
    }
}