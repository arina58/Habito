package com.example.habitstracker.viewModel

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.domain.useCase.SaveUserNameUseCase

class UserNameViewModel: ViewModel() {
    fun saveUserName(name: Editable){
        if(name.length in 1..20) {
            SaveUserNameUseCase().execute(name.toString())
            MAIN.navController.navigate(R.id.action_enterNameFragment_to_homeFragment)
        }
    }
}