package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.*
import com.example.habitstracker.domain.useCase.*

class SettingsViewModel: ViewModel() {
    var stateSwitchTheme = MutableLiveData<Boolean>()
    var stateNotification = MutableLiveData<Boolean>()

    init{
        stateNotification.value = GetReceiveNotificationsUseCase().execute()

        if(GetNameThemeUseCase().execute() == DARK_THEME)
            stateSwitchTheme.value = true
    }

    fun changeNotification(state: Boolean){
        SaveReceiveNotificationsUseCase().execute(state)
        SetNotificationUseCase().execute()
    }

    fun changeTheme (state: Boolean){
        if(state){
            SaveNameThemeUseCase().execute(DARK_THEME)
            SwitchThemeUseCase().execute(DARK_THEME)
        }else{
            SaveNameThemeUseCase().execute(LIGHT_THEME)
            SwitchThemeUseCase().execute(LIGHT_THEME)
        }

        MAIN.vm.theme.value = MAIN.vm.theme.value?.plus(1)
    }
}