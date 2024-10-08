package com.example.habitstracker.presentation.registerFragment


import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.domain.useCase.SaveUserNameUseCase
import javax.inject.Inject

class UserNameViewModel @Inject constructor(
    private val saveUserNameUseCase: SaveUserNameUseCase
): ViewModel() {

    var changeFragment = MutableLiveData<Unit>()

    fun saveUserName(name: Editable?){
        if(name?.length in 1..20) {
            saveUserNameUseCase(name.toString())
            changeFragment.value = Unit
        }
    }
}