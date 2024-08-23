package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.RECEIVE_NOTIFICATION
import javax.inject.Inject

class SaveReceiveNotificationsUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(state: Boolean){
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putBoolean(RECEIVE_NOTIFICATION, state)
        editor?.apply()
    }
}