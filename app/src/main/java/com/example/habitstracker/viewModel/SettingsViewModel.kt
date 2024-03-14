package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.*
import com.example.habitstracker.domain.useCase.*

class SettingsViewModel: ViewModel() {
    var stateSwitchTheme = MutableLiveData<Boolean>()
    var stateSwitchVibration = MutableLiveData<Boolean>()
    var valueSoundSlider = MutableLiveData<Float>()

    init{
        setSwitch()
        setSoundAndVibration()
    }

    private fun setSwitch(){
        if(GetNameThemeUseCase().execute() == DARK_THEME)
            stateSwitchTheme.value = true
    }

    fun changeTheme (state: Boolean){
        SaveNavLocationUseCase().execute(true)
        if(state){
            SaveNameThemeUseCase().execute(DARK_THEME)
            SwitchThemeUseCase().execute(DARK_THEME)
        }else{
            SaveNameThemeUseCase().execute(LIGHT_THEME)
            SwitchThemeUseCase().execute(LIGHT_THEME)
        }
    }

    fun changeVibration (state: Boolean){
        if(state){
            SaveVibrationUseCase().execute(true)
        }else{
            SaveVibrationUseCase().execute(false)
        }
    }
    fun saveSound(value: Float){
        SaveSoundUseCase().execute(value)
    }

    private fun setSoundAndVibration(){
        stateSwitchVibration.value = GetSoundAndVibrationUseCase().getVibration()
        valueSoundSlider.value = GetSoundAndVibrationUseCase().getSound()
    }
}