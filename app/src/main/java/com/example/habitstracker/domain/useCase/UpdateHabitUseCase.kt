package com.example.habitstracker.domain.useCase

import com.example.habitstracker.domain.HabitRepository
import com.example.habitstracker.domain.model.HabitItem

class UpdateHabitUseCase(private val habitRepository: HabitRepository) {
    suspend operator fun invoke(habitItem: HabitItem){
        habitRepository.editHabitItem(habitItem)
    }
}