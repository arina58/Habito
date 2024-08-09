package com.example.habitstracker.domain.dialogs

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.GetHabitItemUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DialogChangeHabitViewModel @Inject constructor(
    private val getHabitItemUseCase: GetHabitItemUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase
) : ViewModel() {

    private var _habit = MutableLiveData<HabitItem>()
    val habit: LiveData<HabitItem>
        get() = _habit

    fun getHabit(id: Int) {
        viewModelScope.launch {
            val item = getHabitItemUseCase(id)
            _habit.value = item
        }
    }

    fun updateHabit(period: Editable?, title: Editable?, description: Editable?) {
        viewModelScope.launch {
            val item = habit.value?.copy(
                title = title.toString(),
                period = period.toString().toInt(),
                description = description.toString()
            )
            if (item != null) updateHabitUseCase(item)
        }
    }
}