package com.example.habitstracker.domain.useCase

import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ValidateUseCase {

    fun validateName(text: TextInputEditText, layout: TextInputLayout): Boolean{
        return when (text.text?.length){
            in 1..30 ->{
                layout.error = null
                true
            }
            else -> {
                layout.error = MAIN.resources.getString(R.string.error_validate_name_goal)
                false
            }
        }
    }

    fun validateDescription(text: TextInputEditText, layout: TextInputLayout): Boolean{
        return when (text.text?.length){
            in 0..255 ->{
                layout.error = null
                true
            }
            else -> {
                layout.error = MAIN.resources.getString(R.string.error_validate_description)
                false
            }
        }
    }

    fun validateNumber(text: TextInputEditText, layout: TextInputLayout): Boolean{
        return if (text.text?.length != 0) {
            when (text.text.toString().toInt()) {
                in 2..255 -> {
                    layout.error = null
                    true
                }
                else -> {
                    layout.error = MAIN.resources.getString(R.string.error_validate_number)
                    false
                }
            }
        }else{
            layout.error = MAIN.resources.getString(R.string.error_validate_number)
            false
        }
    }
}