package com.example.habitstracker.di

import android.app.Application
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.habitstracker.domain.dialogs.DialogAddHabit
import com.example.habitstracker.domain.dialogs.DialogChangeHabit
import com.example.habitstracker.domain.dialogs.DialogFinishHabit
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

    fun inject(dialog: DialogAddHabit)

    fun inject(dialog: DialogChangeHabit)

    fun inject(dialog: DialogFinishHabit)

    @Component.Factory
    interface ApplicationComponentFactory{
        fun create(
            @BindsInstance application: Application,
            @BindsInstance context: Context
        ):  AppComponent
    }

}