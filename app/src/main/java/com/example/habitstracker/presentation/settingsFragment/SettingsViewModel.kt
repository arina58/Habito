package com.example.habitstracker.presentation.settingsFragment

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habitstracker.*
import com.example.habitstracker.domain.useCase.*
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    application: Application,
    private val getReceiveNotificationsUseCase: GetReceiveNotificationsUseCase,
    private val getNameThemeUseCase: GetNameThemeUseCase,
    private val saveReceiveNotificationsUseCase: SaveReceiveNotificationsUseCase,
    private val setNotificationUseCase: SetNotificationUseCase,
    private val saveNameThemeUseCase: SaveNameThemeUseCase,
    private val switchThemeUseCase: SwitchThemeUseCase,
): AndroidViewModel(application) {

    var stateSwitchTheme = MutableLiveData<Boolean>()
    var stateNotification = MutableLiveData<Boolean>()

    init{
        stateNotification.value = getReceiveNotificationsUseCase(application)

        if(getNameThemeUseCase(application) == DARK_THEME)
            stateSwitchTheme.value = true
    }

    fun changeNotification(context: Context, state: Boolean){
        saveReceiveNotificationsUseCase(context, state)
        setNotificationUseCase()
    }

    fun changeTheme (context: Context, state: Boolean){
        if(state){
            saveNameThemeUseCase(context, DARK_THEME)
            switchThemeUseCase(DARK_THEME)
        }else{
            saveNameThemeUseCase(context, LIGHT_THEME)
            switchThemeUseCase(LIGHT_THEME)
        }

//        MAIN.vm.theme.value = MAIN.vm.theme.value?.plus(1)
    }
}