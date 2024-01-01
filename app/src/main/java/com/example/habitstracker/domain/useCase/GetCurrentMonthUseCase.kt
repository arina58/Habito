package com.example.habitstracker.domain.useCase

import java.util.*

class GetCurrentMonthUseCase {

    fun execute(): String {
        val cal: Calendar = Calendar.getInstance()
        val month = when(cal.get(Calendar.MONTH)){
            0 -> "Jan"
            1 -> "Feb"
            2 -> "Mat"
            3 -> "Apr"
            4 -> "May"
            5 -> "Jun"
            6 -> "Jul"
            7 -> "Aug"
            8 -> "Sep"
            9 -> "Oct"
            10 -> "Nov"
            11 -> "Dec"
            else -> ""
        }

        return month
    }
}