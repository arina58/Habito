package com.example.habitstracker.di

import androidx.lifecycle.ViewModel
import com.example.habitstracker.presentation.mainActivity.MainViewModel
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
}