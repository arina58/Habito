package com.example.habitstracker

import android.app.Application
import com.example.habitstracker.di.DaggerAppComponent

class HabitoApp : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this, applicationContext)
    }
}