package com.example.habitstracker.domain.useCase

import com.example.habitstracker.domain.HabitRepository

class DeleteHabitUseCase(private val habitRepository: HabitRepository) {
    suspend operator fun invoke(habitItemId: Int){
        habitRepository.deleteHabitItem(habitItemId)
    }
}