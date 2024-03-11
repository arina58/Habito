package com.example.habitstracker.domain.useCase

import java.util.*

class GetCurrentMonthUseCase {

    fun execute(): MutableList<String> {
        val cal: Calendar = Calendar.getInstance()
        val date = cal.get(Calendar.DATE)
        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        var months_i = mutableListOf<Int>()
        var months = mutableListOf<String>()

        if(date in 1..6){
            for(i in 1 ..(dayOfWeek - 2)) {
                months_i.add((cal.get(Calendar.MONTH) - 1))
            }
        }
        while(months_i.size < 7) {
            months_i.add(cal.get(Calendar.MONTH))
        }

        val titles = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        months_i.forEach {
            months.add(titles[it])
        }
        return months
    }
}