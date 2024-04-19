package com.example.habitstracker.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.STATUS
import com.example.habitstracker.domain.model.HabitFinishItemData
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase

class FinishViewModel: ViewModel() {

    var data = MutableLiveData<ArrayList<HabitFinishItemData>>()
    var update = MutableLiveData<Int>()

    init {
        val dataStart = ArrayList<HabitFinishItemData>()
        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"), MAIN).forEach {
            dataStart.add(HabitFinishItemData(it.id, it.title))
        }

        data.value = dataStart
        update.value = 0
    }

    fun addItem(habit: HabitFinishItemData){
        data.value?.add(habit)
    }

    fun deleteItem(id: Int){
        var item: HabitFinishItemData? = null
        data.value?.forEach {
            if(it.id == id) item = it
        }
        data.value?.remove(item)
        update.value = update.value?.plus(1)
        Log.d("MY", "-1")
    }
}