package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.ID
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
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
    val res = MAIN.resources
    init{
        val dataStart = ArrayList<ProgressData>()

        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0", "1"), MAIN).forEach {
            dataStart.add(ProgressData(it.id, it.title, " ${res.getString(R.string.progress_bar_text)}",
                it.date_of_week, it.description, it.period))
        }

        data.value = dataStart
    }

    fun updateLabel(progressData: ProgressData){
        id = progressData.id
        title.value = "${res.getString(R.string.card_name_goal)} ${progressData.ItemName}"
        description.value = "${res.getString(R.string.card_description_goal)} ${progressData.Description}"
        days.value = "${res.getString(R.string.card_days_goal)} ${progressData.Period}"
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
                    title.value = "${res.getString(R.string.card_name_goal)} ${it.ItemName}"
                    description.value = "${res.getString(R.string.card_description_goal)} ${it.Description}"
                    days.value = "${res.getString(R.string.card_days_goal)} ${it.Period}"
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
            title.value = res.getString(R.string.card_name_goal)
            description.value = res.getString(R.string.card_description_goal)
            days.value = res.getString(R.string.card_days_goal)
        }
    }
    fun addItem(){
        val item = GetLastHabitFromDBUseCase().execute()
        data.value?.add(ProgressData(item.id, item.title, " ${res.getString(R.string.progress_bar_text)}",
            item.date_of_week, item.description, item.period))
    }
}