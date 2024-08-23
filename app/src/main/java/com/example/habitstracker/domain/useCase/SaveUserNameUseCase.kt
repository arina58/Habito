package com.example.habitstracker.domain.useCase

import android.content.Context
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val context: Context
) {
    operator fun invoke(username: String){
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putString("username", username)
        editor?.apply()
    }
}