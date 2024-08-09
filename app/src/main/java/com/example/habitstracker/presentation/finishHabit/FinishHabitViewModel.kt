package com.example.habitstracker.presentation.finishHabit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.DeleteHabitUseCase
import com.example.habitstracker.domain.useCase.GetHabitItemUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import com.example.habitstracker.domain.useCase.ValidateUseCase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinishHabitViewModel @Inject constructor(
    private val getHabitItemUseCase: GetHabitItemUseCase,
    private val validateUseCase: ValidateUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase
) : ViewModel() {
    private var _errorName = MutableLiveData<Boolean>()
    val errorName: LiveData<Boolean>
        get() = _errorName

    private var _errorCount = MutableLiveData<Boolean>()
    val errorCount: LiveData<Boolean>
        get() = _errorCount


    private val _shouldCloseDialog = MutableLiveData<Unit>()
    val shouldCloseDialog: LiveData<Unit>
        get() = _shouldCloseDialog

    private var _habit = MutableLiveData<HabitItem>()
    val habit: LiveData<HabitItem>
        get() = _habit

    fun getHabit(id: Int) {
        viewModelScope.launch {
            val item = getHabitItemUseCase(id)
            _habit.value = item
        }
    }

    fun deleteHabit(id: Int){
        viewModelScope.launch {
            deleteHabitUseCase(id)
        }

        _shouldCloseDialog.value = Unit
    }

    fun updateHabit(
        title: TextInputEditText,
        count: TextInputEditText
    ) {
        if(validate(title, count)) {
            viewModelScope.launch {
                val item = habit.value?.copy(
                    title = title.text.toString(),
                    period = count.text.toString().toInt()
                )
                if (item != null) updateHabitUseCase(item)
            }

            _shouldCloseDialog.value = Unit
        }
    }

    private fun validate(
        title: TextInputEditText,
        count: TextInputEditText
    ): Boolean {
        var result = true
        if (!validateUseCase.validateName(title.text.toString())) {
            _errorName.value = true
            result = false
        }

        if (!validateUseCase.validateNumber(count.text.toString())) {
            _errorCount.value = true
            result = false
        }

        return result
    }

    fun resetErrorName() {
        _errorName.value = false
    }


    fun resetErrorCount() {
        _errorCount.value = false
    }

}