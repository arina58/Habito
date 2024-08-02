package com.example.habitstracker.presentation.analysisFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habitstracker.R
import com.example.habitstracker.STATUS
import com.example.habitstracker.data.HabitRepositoryImpl
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.DeleteHabitUseCase
import com.example.habitstracker.domain.useCase.GetHabitItemUseCase
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase

class AnalysisViewModel(
    application: Application,
    private val getHabitsFromDBUseCase: GetHabitsFromDBUseCase
): AndroidViewModel(application) {

    var data =  getHabitsFromDBUseCase.invoke()
//    private val updateHabitUseCase = UpdateHabitUseCase(habitRepository)
//    private val deleteHabitUseCase = DeleteHabitUseCase(habitRepository)
//    private val getHabitItem = GetHabitItemUseCase(habitRepository)

    private val res = application.resources

    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var days = MutableLiveData<String>()

    var id: Int? = null

    fun updateLabel(habitProgressItem: HabitItem){
        id = habitProgressItem.id
        title.value = "${res.getString(R.string.card_name_goal)} ${habitProgressItem.title}"
        description.value = "${res.getString(R.string.card_description_goal)} ${habitProgressItem.description}"
        days.value = "${res.getString(R.string.card_days_goal)} ${habitProgressItem.period}"
    }

//    fun updateItem(context: Context, id: Int) {
//        val item = getHabitsFromDBUseCase.execute(ID, arrayOf("$id"), context)[0]
//        data.value?.forEach {
//            if(it.id == item.id){
//                it.ItemName = item.title
//                it.Count = item.date_of_week
//                it.Description = item.description
//                it.Period = item.period
//                if (id == item.id) {
//                    title.value = "${res.getString(R.string.card_name_goal)} ${it.ItemName}"
//                    description.value = "${res.getString(R.string.card_description_goal)} ${it.Description}"
//                    days.value = "${res.getString(R.string.card_days_goal)} ${it.Period}"
//                }
//            }
//        }
//    }

//    fun deleteItem(id: Int){
//        var position: ProgressData? = null
//        data.value?.forEach {
//            if(it.id == id) position = it
//        }
//
//        data.value?.remove(position)
//
//        if(id == this.id){
//            title.value = res.getString(R.string.card_name_goal)
//            description.value = res.getString(R.string.card_description_goal)
//            days.value = res.getString(R.string.card_days_goal)
//        }
//    }
//    fun addItem(){
//        val item = GetLastHabitFromDBUseCase().execute()
//        data.value?.add(ProgressData(item.id, item.title, " ${res.getString(R.string.progress_bar_text)}",
//            item.date_of_week, item.description, item.period))
//    }
}