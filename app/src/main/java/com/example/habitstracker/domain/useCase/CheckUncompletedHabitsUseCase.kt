package com.example.habitstracker.domain.useCase

import com.example.habitstracker.STATUS

class CheckUncompletedHabitsUseCase {
    fun execute():Boolean{
        var result = false
        if(GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0")).size == 0) result = true
        return result
    }
}