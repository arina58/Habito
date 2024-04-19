package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.ID
import com.example.habitstracker.MAIN
import com.example.habitstracker.STATUS
import com.example.habitstracker.domain.model.ProgressData
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.GetLastHabitFromDBUseCase

class AnalysisViewModel: ViewModel() {

    var data = MutableLiveData<ArrayList<ProgressData>>()
    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var days = MutableLiveData<String>()

    var id: Int? = null
    init{
        val dataStart = ArrayList<ProgressData>()

        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0", "1"), MAIN).forEach {
            dataStart.add(ProgressData(it.id, it.title, " from 7 days target",
                it.date_of_week, it.description, it.period))
        }

        data.value = dataStart
    }

    fun updateLabel(progressData: ProgressData){
        id = progressData.id
        title.value = "Habit name: ${progressData.ItemName}"
        description.value = "Description: ${progressData.Description}"
        days.value = "Days left: ${progressData.Period}"
    }
    fun updateItem(id: Int) {
        val item = GetHabitsFromDBUseCase().execute(ID, arrayOf("$id"), MAIN)
        data.value?.forEach {
            if(it.id == item[0].id){
                it.ItemName = item[0].title
                it.Count = item[0].date_of_week
                it.Description = item[0].description
                it.Period = item[0].period
                if (id == item[0].id) {
                    title.value = "Habit name: ${it.ItemName}"
                    description.value = "Description: ${it.Description}"
                    days.value = "Days left: ${it.Period}"
                }
            }
        }
    }

    fun deleteItem(id: Int){
        var position: ProgressData? = null
        data.value?.forEach {
            if(it.id == id) position = it
        }

        data.value?.remove(position)

        if(id == this.id){
            title.value = "Habit name:"
            description.value = "Description:"
            days.value = "Days left:"
        }
    }
    fun addItem(){
        val item = GetLastHabitFromDBUseCase().execute()
        data.value?.add(ProgressData(item.id, item.title, " from 7 days target",
            item.date_of_week, item.description, item.period))
    }
}