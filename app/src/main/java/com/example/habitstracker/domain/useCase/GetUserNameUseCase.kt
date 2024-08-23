package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.DEFAULT_NAME
import com.example.habitstracker.USER_NAME
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val context: Context
) {

    operator fun invoke(): String? {
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getString(USER_NAME, DEFAULT_NAME)
    }

}