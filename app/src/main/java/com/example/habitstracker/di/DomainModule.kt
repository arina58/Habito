package com.example.habitstracker.di

import com.example.habitstracker.data.HabitRepositoryImpl
import com.example.habitstracker.domain.HabitRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindHabitRepository(impl: HabitRepositoryImpl): HabitRepository
}