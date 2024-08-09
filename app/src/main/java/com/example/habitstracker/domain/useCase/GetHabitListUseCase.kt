package com.example.habitstracker.domain.useCase

import androidx.lifecycle.LiveData
import com.example.habitstracker.domain.HabitRepository
import com.example.habitstracker.domain.model.HabitItem
import javax.inject.Inject

class GetHabitListUseCase @Inject constructor(private val habitRepository: HabitRepository) {

    operator fun invoke(): LiveData<List<HabitItem>> {
        return habitRepository.getHabitList()
    }
}