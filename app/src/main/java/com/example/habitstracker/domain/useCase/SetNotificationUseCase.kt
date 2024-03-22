package com.example.habitstracker.domain.useCase

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.habitstracker.MAIN
import com.example.habitstracker.domain.Notifications

class SetNotificationUseCase {
    private val alarmManager = MAIN.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val alarmIntent = Intent(MAIN, Notifications::class.java).let { intent ->
        PendingIntent.getBroadcast(MAIN, 0, intent, 0)
    }

    fun execute(){
       if(GetReceiveNotificationsUseCase().execute()) setNotification()
        else removeNotification()
    }

    private fun removeNotification(){
        alarmManager.cancel(alarmIntent)
    }

    private fun setNotification(){
        val timeIntervalMillis = 3 * 60 * 60 * 1000
        val triggerAtMillis = System.currentTimeMillis() + timeIntervalMillis

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            timeIntervalMillis.toLong(),
            alarmIntent
        )
    }
}