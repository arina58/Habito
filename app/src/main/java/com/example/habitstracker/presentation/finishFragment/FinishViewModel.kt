package com.example.habitstracker.presentation.finishFragment

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habitstracker.ID
import com.example.habitstracker.STATUS
import com.example.habitstracker.data.HabitRepositoryImpl
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.GetHabitItemUseCase
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import kotlinx.coroutines.launch

class FinishViewModel(application: Application): AndroidViewModel(application) {

    private val habitRepository = HabitRepositoryImpl(application)

    var data = GetHabitsFromDBUseCase(habitRepository).invoke()

    private val updateHabitUseCase = UpdateHabitUseCase(habitRepository)
    private val getHabitItemUseCase = GetHabitItemUseCase(habitRepository)


//    fun addItem(habit: HabitFinishItemData){
//        data.value?.add(habit)
//    }
//
//    fun deleteItem(id: Int){
//        var item: HabitFinishItemData? = null
//        data.value?.forEach {
//            if(it.id == id) item = it
//        }
//        data.value?.remove(item)
//    }

    fun returnHabit( id: Int){
        viewModelScope.launch {
            val item = getHabitItemUseCase(id)
            item.status = 0
            item.date_of_week -= 1
            updateHabitUseCase(item)
        }
//        MAIN.vmFinish?.deleteItem(item[0].id)
//        MAIN.vmAnalysis?.updateItem(id)
    }
}