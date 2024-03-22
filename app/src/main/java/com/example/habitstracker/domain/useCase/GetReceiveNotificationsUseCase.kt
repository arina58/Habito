package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.*

class GetReceiveNotificationsUseCase {
    fun execute(): Boolean {
        val pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getBoolean(RECEIVE_NOTIFICATION, DEFAULT_RECEIVE)
    }
}