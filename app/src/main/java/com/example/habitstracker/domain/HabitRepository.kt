package com.example.habitstracker.domain

import androidx.lifecycle.LiveData
import com.example.habitstracker.domain.model.HabitItem

interface HabitRepository {

    fun getHabitList(): LiveData<List<HabitItem>>

    suspend fun getHabitItem(habitItemId: Int): HabitItem

    suspend fun addHabitItem(habitItem: HabitItem)

    suspend fun deleteHabitItem(habitItemId: Int)

    suspend fun editHabitItem(habitItem: HabitItem)

    fun getNotCompletedHabitList(): LiveData<List<HabitItem>>

    fun getCompletedHabitList(): LiveData<List<HabitItem>>
}