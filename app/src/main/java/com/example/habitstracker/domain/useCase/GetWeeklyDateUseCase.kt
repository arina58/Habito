package com.example.habitstracker.domain.useCase

import java.util.*

class GetWeeklyDateUseCase {
    private val cal: Calendar = Calendar.getInstance()

    fun execute(): MutableList<Int> {
        val date = cal.get(Calendar.DATE)

        val days = mutableListOf<Int>()
        val sub = getDayInWeek()


        (0..6).forEach {
            days.add(it, date - sub + it)
        }

        return days
    }

    fun getDayInWeek(): Int{
        val dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK)
        var sub = 0

        when(dayOfWeek){
            Calendar.TUESDAY -> sub = 1
            Calendar.WEDNESDAY -> sub = 2
            Calendar.THURSDAY -> sub = 3
            Calendar.FRIDAY -> sub = 4
            Calendar.SATURDAY -> sub = 5
            Calendar.SUNDAY -> sub = 6
        }

        return sub
    }

}