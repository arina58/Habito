package com.example.habitstracker.domain.useCase

import javax.inject.Inject

class ValidateUseCase @Inject constructor() {

    fun validateName(text: String): Boolean{
        return when (text.length){
            in 1..30 ->{
                true
            }
            else -> {
                false
            }
        }
    }

    fun validateDescription(text: String): Boolean{
        return when (text.length){
            in 0..255 ->{
                true
            }
            else -> {
                false
            }
        }
    }

    fun validateNumber(text: String): Boolean{
        return if (text.isNotEmpty()) {
            when (text.toInt()) {
                in 2..365 -> {
                    true
                }
                else -> { false
                }
            }
        }else{
            false
        }
    }
}