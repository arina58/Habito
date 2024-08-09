package com.example.habitstracker.presentation.analysisFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habitstracker.R
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.GetHabitListUseCase
import javax.inject.Inject

class AnalysisViewModel @Inject constructor(
    application: Application,
    getHabitsFromDBUseCase: GetHabitListUseCase
): AndroidViewModel(application) {

    var data =  getHabitsFromDBUseCase.invoke()

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

    fun checkLabel(){
        var flag = false
        for(item in data.value!!){
            if(item.id == id) {
                flag = true
                break
            }
        }
        if (!flag){
            title.value = "${res.getString(R.string.card_name_goal)} "
            description.value = "${res.getString(R.string.card_description_goal)} "
            days.value = "${res.getString(R.string.card_days_goal)} "
        }
    }

}