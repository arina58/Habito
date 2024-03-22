package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.STATUS
import com.example.habitstracker.domain.model.HabitFinishItemData
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase

class FinishViewModel: ViewModel() {

    var data = MutableLiveData<ArrayList<HabitFinishItemData>>()

    init {
        val dataStart = ArrayList<HabitFinishItemData>()
        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"), MAIN).forEach {
            dataStart.add(HabitFinishItemData(it.id, it.title))
        }

        data.value = dataStart
    }
}