package com.example.habitstracker.domain.useCase

import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import java.util.*

class GetCurrentMonthUseCase {

    fun execute(): MutableList<String> {
        val cal: Calendar = Calendar.getInstance()
        val date = cal.get(Calendar.DATE)
        val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        val monthsI = mutableListOf<Int>()
        val months = mutableListOf<String>()

        if(date in 1..6){
            for(i in 1 ..(dayOfWeek - 2)) {
                monthsI.add((cal.get(Calendar.MONTH) - 1))
            }
        }
        while(monthsI.size < 7) {
            monthsI.add(cal.get(Calendar.MONTH))
        }
        val titles = MAIN.resources.getStringArray(R.array.month)
        monthsI.forEach {
            months.add(titles[it])
        }
        return months
    }
}