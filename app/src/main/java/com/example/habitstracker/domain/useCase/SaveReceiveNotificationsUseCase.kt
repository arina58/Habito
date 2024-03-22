package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.MAIN
import com.example.habitstracker.RECEIVE_NOTIFICATION

class SaveReceiveNotificationsUseCase {
    fun execute(state: Boolean){
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putBoolean(RECEIVE_NOTIFICATION, state)
        editor?.apply()
    }
}