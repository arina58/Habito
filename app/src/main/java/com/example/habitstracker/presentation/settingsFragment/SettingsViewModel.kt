package com.example.habitstracker.presentation.settingsFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habitstracker.*
import com.example.habitstracker.domain.useCase.*
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    application: Application,
    getReceiveNotificationsUseCase: GetReceiveNotificationsUseCase,
    getNameThemeUseCase: GetNameThemeUseCase,
    private val saveReceiveNotificationsUseCase: SaveReceiveNotificationsUseCase,
    private val setNotificationUseCase: SetNotificationUseCase,
    private val saveNameThemeUseCase: SaveNameThemeUseCase,
    private val switchThemeUseCase: SwitchThemeUseCase,
): AndroidViewModel(application) {

    var stateSwitchTheme = MutableLiveData<Boolean>()
    var stateNotification = MutableLiveData<Boolean>()

    init{
        stateNotification.value = getReceiveNotificationsUseCase()

        if(getNameThemeUseCase() == DARK_THEME)
            stateSwitchTheme.value = true
    }

    fun changeNotification(state: Boolean){
        saveReceiveNotificationsUseCase(state)
        setNotificationUseCase()
    }

    fun changeTheme (state: Boolean){
        if(state){
            saveNameThemeUseCase(DARK_THEME)
            switchThemeUseCase(DARK_THEME)
        }else{
            saveNameThemeUseCase(LIGHT_THEME)
            switchThemeUseCase(LIGHT_THEME)
        }
    }
}