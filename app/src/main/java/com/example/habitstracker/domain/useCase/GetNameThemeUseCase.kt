package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.NAME_THEME
import javax.inject.Inject

class GetNameThemeUseCase @Inject constructor() {

    operator fun invoke(context: Context): Boolean {
        val pref = context.getSharedPreferences("User", Context.MODE_PRIVATE)
        return pref.getBoolean(NAME_THEME, false)
    }

}