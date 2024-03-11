package com.example.habitstracker.domain.useCase

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.habitstracker.MAIN
import com.example.habitstracker.MidnightTaskReceiver
import java.util.*

class SetTaskReceiverUseCase {
    fun execute(){
        val alarmMgr = MAIN.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(MAIN, MidnightTaskReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(MAIN, 0, intent, 0)
        }

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 17)
            set(Calendar.MINUTE, 1)
            set(Calendar.SECOND, 0)
        }

        alarmMgr.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
    }
}