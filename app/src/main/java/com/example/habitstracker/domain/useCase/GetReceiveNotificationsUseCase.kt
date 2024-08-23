package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.*
import javax.inject.Inject

class GetReceiveNotificationsUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(): Boolean {
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getBoolean(RECEIVE_NOTIFICATION, DEFAULT_RECEIVE)
    }
}