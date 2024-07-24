package com.example.habitstracker.domain.useCase

import com.example.habitstracker.domain.HabitRepository
import com.example.habitstracker.domain.model.HabitItem

class GetHabitItemUseCase(private val habitRepository: HabitRepository) {

    suspend operator fun invoke(habitItemId: Int): HabitItem{
        return habitRepository.getHabitItem(habitItemId)
    }
}