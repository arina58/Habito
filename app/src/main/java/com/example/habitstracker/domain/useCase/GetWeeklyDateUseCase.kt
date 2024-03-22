package com.example.habitstracker.domain.useCase

import java.util.*

class GetWeeklyDateUseCase {
    private val cal: Calendar = Calendar.getInstance()
    private val dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK) - 1
    fun execute(): MutableList<Int> {


        val days = mutableListOf<Int>()

        cal.add(Calendar.DAY_OF_MONTH, -(dayOfWeek - 1))
        for(i in 1..7){
            days.add(cal.get(Calendar.DAY_OF_MONTH))
            cal.add(Calendar.DAY_OF_MONTH, 1)
        }

        return days
    }

    fun getDayInWeek(): Int {
        return dayOfWeek
    }
}