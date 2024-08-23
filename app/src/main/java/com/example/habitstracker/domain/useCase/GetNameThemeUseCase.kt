package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.NAME_THEME
import javax.inject.Inject

class GetNameThemeUseCase @Inject constructor(
    private val context: Context
) {

    operator fun invoke(): Boolean {
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getBoolean(NAME_THEME, false)
    }

}