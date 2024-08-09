package com.example.habitstracker.di

import androidx.lifecycle.ViewModel
import com.example.habitstracker.domain.dialogs.DialogChangeHabitViewModel
import com.example.habitstracker.presentation.analysisFragment.AnalysisViewModel
import com.example.habitstracker.presentation.finishFragment.FinishViewModel
import com.example.habitstracker.presentation.homeFragment.HomeViewModel
import com.example.habitstracker.presentation.mainActivity.MainViewModel
import com.example.habitstracker.presentation.registerFragment.UserNameViewModel
import com.example.habitstracker.presentation.settingsFragment.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("MainViewModel")
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @StringKey("AnalysisViewModel")
    @Binds
    fun bindAnalysisViewModel(impl: AnalysisViewModel): ViewModel

    @IntoMap
    @StringKey("FinishViewModel")
    @Binds
    fun bindFinishViewModel(impl: FinishViewModel): ViewModel

    @IntoMap
    @StringKey("HomeViewModel")
    @Binds
    fun bindHomeViewModel(impl: HomeViewModel): ViewModel

    @IntoMap
    @StringKey("UserNameViewModel")
    @Binds
    fun bindRegisterViewModel(impl: UserNameViewModel): ViewModel

    @IntoMap
    @StringKey("SettingsViewModel")
    @Binds
    fun bindSettingsViewModel(impl: SettingsViewModel): ViewModel

    @IntoMap
    @StringKey("DialogChangeHabitViewModel")
    @Binds
    fun bindDialogChangeViewModel(impl: DialogChangeHabitViewModel): ViewModel
}