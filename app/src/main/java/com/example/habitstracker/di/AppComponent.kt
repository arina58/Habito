package com.example.habitstracker.di

import android.app.Activity
import dagger.Component

@Component
interface AppComponent {

    fun inject(activity: Activity)
}