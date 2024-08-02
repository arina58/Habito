package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.R
import java.util.*
import javax.inject.Inject

class GetCurrentMonthUseCase @Inject constructor() {

    operator fun invoke(context: Context): List<String> {
        val cal: Calendar = Calendar.getInstance()
        val date = cal.get(Calendar.DATE)
        val monthsI = mutableListOf<Int>()
        val months = mutableListOf<String>()
        val dates = GetWeeklyDateUseCase().execute()
        var flag = false
        for (i in 1..6) {
            if (dates[i - 1] > dates[i]) flag = true
        }
        if (flag){
            if(date > 22) {
                monthsI.add((cal.get(Calendar.MONTH)))
                for (i in 1..6) {
                    if (dates[i - 1] < dates[i])  monthsI.add((cal.get(Calendar.MONTH)))
                    else
                        break
                }
                while (monthsI.size < 7)  monthsI.add((cal.get(Calendar.MONTH)) + 1)
            }else{
                monthsI.add((cal.get(Calendar.MONTH)) - 1)
                for (i in 1..6) {
                    if (dates[i - 1] < dates[i])  monthsI.add((cal.get(Calendar.MONTH)) - 1)
                    else
                        break
                }
                while (monthsI.size < 7)  monthsI.add((cal.get(Calendar.MONTH)))
            }

        }else{
            for (i in 1..7) monthsI.add((cal.get(Calendar.MONTH)))
        }

        while(monthsI.size < 7) {
            monthsI.add(cal.get(Calendar.MONTH))
        }
        val titles = context.resources.getStringArray(R.array.month)

        monthsI.forEach {
            months.add(titles[it])
        }
        return months.toList()
    }
}