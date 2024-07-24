package com.example.habitstracker.domain.useCase

import android.content.Context

class SaveUserNameUseCase {
    operator fun invoke(context: Context, username: String){
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putString("username", username)
        editor?.apply()
    }
}