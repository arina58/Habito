package com.example.habitstracker.viewModel

import androidx.lifecycle.ViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.domain.useCase.SaveUserNameUseCase

class UserNameViewModel: ViewModel() {

    fun saveUserName(name: String){
        if(name != "") {
            SaveUserNameUseCase().execute(name)
            MAIN.navController.navigate(R.id.action_enterNameFragment_to_homeFragment)
        }
    }
}