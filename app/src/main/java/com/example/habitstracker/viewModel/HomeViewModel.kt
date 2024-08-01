package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
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
    val res = MAIN.resources

    init{
        userName.value = "${res.getString(R.string.hello)} ${GetUserNameUseCase().execute()}"
        timeTitle.value = getTimeTitle()

        weeklyDate.value = GetWeeklyDateUseCase().execute()
        currentMonth.value = GetCurrentMonthUseCase().execute()
        dayInWeek.value = GetWeeklyDateUseCase().getDayInWeek()
        overallStreak.value = "${res.getString(R.string.overall_streak)} ${streaks[0]} ${getTextDay(streaks[0])}"
        bestStreak.value = "${res.getString(R.string.best_streak)} ${streaks[1]} ${getTextDay(streaks[1])}"
        updateCheckList.value = 0

        val dataStart = ArrayList<ItemsData>()
        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"), MAIN).forEach {
            dataStart.add(ItemsData(
                it.id, it.title,
                "${res.getString(R.string.current)} ${it.current}",
                "${res.getString(R.string.best)} ${it.best}"))
        }
        data.value = dataStart

        doneHabits.value = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"), MAIN).size
        notDoneHabits.value = data.value?.size
        updateLabel()
    }

    private fun getTextDay(day: Int): String {
        return when (day % 10){
            0 -> res.getStringArray(R.array.days)[2]
            1 -> res.getStringArray(R.array.days)[0]
            in 2..4 -> res.getStringArray(R.array.days)[1]
            in 5..9 -> res.getStringArray(R.array.days)[2]
            else -> ""
        }
    }

    private fun updateLabel(){
        label.value = "${doneHabits.value!!} ${res.getStringArray(R.array.chart_text)[0]} " +
                "${doneHabits.value!! + data.value!!.size} ${res.getStringArray(R.array.chart_text)[1]}"
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
                    "${res.getString(R.string.current)} ${habit.current}",
                    "${res.getString(R.string.best)} ${habit.best}"
                )
            )
            updateChart(1)
        }else if (action == 0){
            data.value?.forEach{
                if(it.id == item?.id) it.NameItem = item.title
            }
        }
        else if(action == -1){
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
        val text = res.getStringArray(R.array.time_text)
        return when(cal.get(Calendar.HOUR_OF_DAY)){
            in 0..3 -> text[0]
            in 4..11 -> text[1]
            in 12..15 -> text[2]
            in 16..21 -> text[3]
            in 22..23 -> text[0]
            else -> ""
        }
    }
}