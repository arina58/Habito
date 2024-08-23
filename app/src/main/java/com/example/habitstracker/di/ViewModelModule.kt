package com.example.habitstracker.di

import androidx.lifecycle.ViewModel
import com.example.habitstracker.presentation.addHabitDialog.AddHabitViewModel
import com.example.habitstracker.presentation.analysisFragment.AnalysisViewModel
import com.example.habitstracker.presentation.changeHabitDialog.ChangeHabitViewModel
import com.example.habitstracker.presentation.finishFragment.FinishViewModel
import com.example.habitstracker.presentation.finishHabit.FinishHabitViewModel
import com.example.habitstracker.presentation.homeFragment.HomeViewModel
import com.example.habitstracker.presentation.mainActivity.MainViewModel
import com.example.habitstracker.presentation.registerFragment.UserNameViewModel
import com.example.habitstracker.presentation.settingsFragment.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AnalysisViewModel::class)
    @Binds
    fun bindAnalysisViewModel(impl: AnalysisViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FinishViewModel::class)
    @Binds
    fun bindFinishViewModel(impl: FinishViewModel): ViewModel

    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @Binds
    fun bindHomeViewModel(impl: HomeViewModel): ViewModel

    @IntoMap
    @ViewModelKey(UserNameViewModel::class)
    @Binds
    fun bindRegisterViewModel(impl: UserNameViewModel): ViewModel

    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    @Binds
    fun bindSettingsViewModel(impl: SettingsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ChangeHabitViewModel::class)
    @Binds
    fun bindDialogChangeViewModel(impl: ChangeHabitViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AddHabitViewModel::class)
    @Binds
    fun bindDialogAddViewModel(impl: AddHabitViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FinishHabitViewModel::class)
    @Binds
    fun bindDialogFinishViewModel(impl: FinishHabitViewModel): ViewModel
}