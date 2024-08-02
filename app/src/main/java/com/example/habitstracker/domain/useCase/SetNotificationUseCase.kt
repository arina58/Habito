package com.example.habitstracker.domain.useCase

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.habitstracker.domain.Notifications
import javax.inject.Inject

class SetNotificationUseCase @Inject constructor(
    private val context: Context,
    private val getReceiveNotificationsUseCase: GetReceiveNotificationsUseCase
) {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val alarmIntent = Intent(context, Notifications::class.java).let { intent ->
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    operator fun invoke(){
       if(getReceiveNotificationsUseCase(context)) setNotification()
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