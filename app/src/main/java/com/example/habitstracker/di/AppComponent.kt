package com.example.habitstracker.di

import android.app.Application
import android.content.Context
import com.example.habitstracker.presentation.addHabitDialog.AddHabit
import com.example.habitstracker.presentation.changeHabitDialog.ChangeHabit
import com.example.habitstracker.presentation.finishHabit.FinishHabit
import com.example.habitstracker.presentation.analysisFragment.AnalysisFragment
import com.example.habitstracker.presentation.finishFragment.FinishFragment
import com.example.habitstracker.presentation.homeFragment.HomeFragment
import com.example.habitstracker.presentation.mainActivity.MainActivity
import com.example.habitstracker.presentation.registerFragment.UserNameFragment
import com.example.habitstracker.presentation.settingsFragment.SettingsFragment
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component (modules = [ViewModelModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: HomeFragment)

    fun inject(fragment: AnalysisFragment)

    fun inject(fragment: FinishFragment)

    fun inject(fragment: UserNameFragment)

    fun inject(fragment: SettingsFragment)

    fun inject(dialog: AddHabit)

    fun inject(dialog: ChangeHabit)

    fun inject(dialog: FinishHabit)

    @Component.Factory
    interface ApplicationComponentFactory{
        fun create(
            @BindsInstance application: Application,
            @BindsInstance context: Context
        ):  AppComponent
    }

}