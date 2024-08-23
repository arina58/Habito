package com.example.habitstracker.presentation.finishFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitstracker.domain.useCase.GetCompletedHabitListUseCase
import com.example.habitstracker.domain.useCase.GetHabitItemUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinishViewModel @Inject constructor(
    getCompletedHabitUseCase: GetCompletedHabitListUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val getHabitItemUseCase: GetHabitItemUseCase
): ViewModel() {

    var data = getCompletedHabitUseCase.invoke()

    fun returnHabit(id: Int){
        viewModelScope.launch {
            val habit = getHabitItemUseCase(id)
            updateHabitUseCase(habit.copy(status = 0, dateOfWeek = habit.dateOfWeek - 1))
        }
    }
}