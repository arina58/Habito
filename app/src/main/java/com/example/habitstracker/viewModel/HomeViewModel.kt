package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.STATUS
import com.example.habitstracker.domain.model.HabitData
import com.example.habitstracker.domain.model.ItemsData
import com.example.habitstracker.domain.useCase.*
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel: ViewModel(){

    var userName = MutableLiveData<String>()
    var timeTitle = MutableLiveData<String>()
    var overallStreak = MutableLiveData<String>()
    var bestStreak = MutableLiveData<String>()
    var label = MutableLiveData<String>()
    var dayInWeek = MutableLiveData<Int>()
    var weeklyDate = MutableLiveData<MutableList<Int>>()
    var currentMonth = MutableLiveData<MutableList<String>>()
    var doneHabits = MutableLiveData<Int>()
    var notDoneHabits = MutableLiveData<Int>()
    var updateCheckList = MutableLiveData<Int>()
    var data =  MutableLiveData<MutableList<ItemsData>>()

    private val streaks = GetStreakUseCase().execute()

    init{
        userName.value = GetUserNameUseCase().execute()
        weeklyDate.value = GetWeeklyDateUseCase().execute()
        currentMonth.value = GetCurrentMonthUseCase().execute()
        dayInWeek.value = GetWeeklyDateUseCase().getDayInWeek()
        timeTitle.value = getTimeTitle()
        overallStreak.value = "Overall streak: ${streaks[0]} days"
        bestStreak.value = "Overall best streak: ${streaks[1]} days"
        updateCheckList.value = 0

        val dataStart = ArrayList<ItemsData>()
        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"), MAIN).forEach {
            dataStart.add(ItemsData(
                it.id, it.title,
                "Current: ${it.current}",
                "Best: ${it.best}"))
        }
        data.value = dataStart

        doneHabits.value = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"), MAIN).size
        notDoneHabits.value = data.value?.size
        updateLabel()
    }

    private fun updateLabel(){
        label.value = "${doneHabits.value!!} of " +
                "${doneHabits.value!! + data.value!!.size} habits"
    }

    fun updateChart(value: Int){
        notDoneHabits.value = notDoneHabits.value?.plus(value)
        updateLabel()
    }

    fun updateDone(){
        doneHabits.value = doneHabits.value?.plus(1)
        updateLabel()
    }

    fun updateData(action: Int, item: HabitData?){
        if (action == 1) {
            val habit = GetLastHabitFromDBUseCase().execute()
            data.value?.add(
                ItemsData(habit.id, habit.title,
                    "Current: ${habit.current}",
                    "Best: ${habit.best}"
                )
            )
            updateChart(1)
        }else if (action == 0){
            data.value?.forEach{
                if(it.id == item?.id) it.NameItem = item.title
            }
        }else if(action == -1){
            var itemR: ItemsData? = null
            data.value?.forEach{
                if(it.id == item?.id) itemR = it
            }
            data.value?.remove(itemR)
        }
        updateCheckList.value = updateCheckList.value?.plus(1)
    }

    private fun getTimeTitle(): String{
        val cal: Calendar = Calendar.getInstance()

        return when(cal.get(Calendar.HOUR_OF_DAY)){
            in 0..3 -> "Good Night"
            in 4..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..21 -> "Good Evening"
            in 22..23 -> "Good Night"
            else -> "error"
        }
    }
}