package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.RECEIVE_NOTIFICATION

class SaveReceiveNotificationsUseCase {
    operator fun invoke(context: Context, state: Boolean){
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putBoolean(RECEIVE_NOTIFICATION, state)
        editor?.apply()
    }
}