package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.DEFAULT_NAME
import com.example.habitstracker.MAIN
import com.example.habitstracker.USER_NAME

class GetUserNameUseCase {

    fun execute(): String? {
        var pref = MAIN.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getString(USER_NAME, DEFAULT_NAME)
    }

}