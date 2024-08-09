package com.example.habitstracker.presentation.finishFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitstracker.domain.useCase.GetCompletedHabitListUseCase
import com.example.habitstracker.domain.useCase.GetHabitItemUseCase
import com.example.habitstracker.domain.useCase.GetHabitListUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinishViewModel @Inject constructor(
    application: Application,
    private val getCompletedHabitUseCase: GetCompletedHabitListUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val getHabitItemUseCase: GetHabitItemUseCase
): AndroidViewModel(application) {

    var data = getCompletedHabitUseCase.invoke()

    fun returnHabit(id: Int){
        viewModelScope.launch {
            val habit = getHabitItemUseCase(id)
            updateHabitUseCase(habit.copy(status = 0, date_of_week = habit.date_of_week - 1))
        }
    }
}