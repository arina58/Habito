package com.example.habitstracker.domain.useCase

import java.util.*

class GetWeeklyDateUseCase {
    private val cal: Calendar = Calendar.getInstance()
    private val dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK) - 1
    fun execute(): MutableList<Int> {

        var days = mutableListOf<Int>()
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        if (dayOfWeek != Calendar.MONDAY) {
            cal.add(Calendar.DAY_OF_MONTH, Calendar.MONDAY - dayOfWeek)
        }

        for (i in 0..6) {
            days.add(cal.get(Calendar.DAY_OF_MONTH) + i)
        }

        return days
    }

    fun getDayInWeek(): Int {
        return dayOfWeek
    }
}