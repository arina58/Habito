package com.example.habitstracker.viewModel

import androidx.lifecycle.ViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.domain.useCase.SaveUserNameUseCase
import com.example.habitstracker.domain.useCase.ValidateUseCase
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class UserNameViewModel: ViewModel() {

    fun checkName(NameText: TextInputEditText, Name: TextInputLayout){
        if (ValidateUseCase().validateName(NameText, Name)){
            SaveUserNameUseCase().execute(NameText.text.toString())
            MAIN.navController.navigate(R.id.action_enterNameFragment_to_homeFragment)
        }
    }
}