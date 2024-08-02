package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.*

class GetReceiveNotificationsUseCase {
    operator fun invoke(context: Context): Boolean {
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getBoolean(RECEIVE_NOTIFICATION, DEFAULT_RECEIVE)
    }
}