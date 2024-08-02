package com.example.habitstracker.domain.useCase

import com.example.habitstracker.domain.HabitRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(private val habitRepository: HabitRepository) {
    suspend operator fun invoke(habitItemId: Int){
        habitRepository.deleteHabitItem(habitItemId)
    }
}