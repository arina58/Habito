package com.example.habitstracker.di

import android.app.Application
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.habitstracker.presentation.homeFragment.HomeFragment
import com.example.habitstracker.presentation.mainActivity.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component (modules = [ViewModelModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface ApplicationComponentFactory{
        fun create(
            @BindsInstance application: Application,
            @BindsInstance context: Context
        ):  AppComponent
    }

}