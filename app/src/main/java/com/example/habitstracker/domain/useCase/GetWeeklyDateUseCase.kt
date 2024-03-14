package com.example.habitstracker.domain.useCase

import java.util.*
import java.util.Calendar.DAY_OF_MONTH

class GetWeeklyDateUseCase {
    private val cal: Calendar = Calendar.getInstance()
    private val dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK) - 1
    fun execute(): MutableList<Int> {


        var days = mutableListOf<Int>()
        cal.add(Calendar.DAY_OF_WEEK, Calendar.MONDAY - dayOfWeek)

        for(i in 1..7){
            days.add(cal[DAY_OF_MONTH])
            cal.add(Calendar.DAY_OF_WEEK, 1)
        }

        return days
    }

    fun getDayInWeek(): Int {
        return dayOfWeek
    }
}