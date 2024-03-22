package com.example.habitstracker.domain.useCase

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.habitstracker.MAIN
import com.example.habitstracker.domain.MidnightProgress
import java.util.*

class SetMidnightProgressUseCase {

    fun execute(){
        val alarmManager = MAIN.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(MAIN, MidnightProgress::class.java).let { intent ->
            PendingIntent.getBroadcast(MAIN, 0, intent,0)
        }

        val midnight = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (midnight.before(Calendar.getInstance())) {
            midnight.add(Calendar.DAY_OF_MONTH, 1)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            midnight.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
    }
}