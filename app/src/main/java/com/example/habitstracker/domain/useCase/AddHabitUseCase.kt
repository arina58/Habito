package com.example.habitstracker.domain.useCase

import com.example.habitstracker.domain.HabitRepository
import com.example.habitstracker.domain.model.HabitItem
import javax.inject.Inject

class AddHabitUseCase @Inject constructor(private val habitRepository: HabitRepository) {
    suspend operator fun invoke(habitItem: HabitItem){
        habitRepository.addHabitItem(habitItem)
    }
}