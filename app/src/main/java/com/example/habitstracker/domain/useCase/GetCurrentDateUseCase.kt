package com.example.habitstracker.domain.useCase

import java.util.*

class GetCurrentDateUseCase {

    fun execute(): Int {
        val cal: Calendar = Calendar.getInstance()
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