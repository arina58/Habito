package com.example.habitstracker.presentation.homeFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habitstracker.R
import com.example.habitstracker.data.HabitRepositoryImpl
import com.example.habitstracker.domain.useCase.*
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(
    application: Application,
    private val getStreakUseCase: GetStreakUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getCurrentMonthUseCase: GetCurrentMonthUseCase,
    private val getWeeklyDateUseCase: GetWeeklyDateUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val getHabitItem: GetHabitItemUseCase,
    private val getHabitsFromDBUseCase: GetHabitsFromDBUseCase
): AndroidViewModel(application){

    private val streaks = getStreakUseCase(application)
    private val res = application.resources

    var userName = "${res.getString(R.string.hello)} ${getUserNameUseCase(application)}"
    var currentMonth = getCurrentMonthUseCase(application)
    var dayInWeek = getWeeklyDateUseCase.getDayInWeek()
    var weeklyDate = getWeeklyDateUseCase.execute()
    var timeTitle = MutableLiveData<String>()
    var overallStreak = "${res.getString(R.string.overall_streak)} ${streaks[0]} ${getTextDay(streaks[0])}"
    var bestStreak = "${res.getString(R.string.best_streak)} ${streaks[1]} ${getTextDay(streaks[1])}"


    var data =  getHabitsFromDBUseCase.invoke()


//    var label = MutableLiveData<String>()
//    var doneHabits = MutableLiveData<Int>()
//    var notDoneHabits = MutableLiveData<Int>()
//    var updateCheckList = MutableLiveData<Int>()


    init{
//        userName.value = "${res.getString(R.string.hello)} ${getUserNameUseCase(application)}"
//        weeklyDate.value = getWeeklyDateUseCase.execute()
//        currentMonth.value = getCurrentMonthUseCase(application)
//        dayInWeek.value = getWeeklyDateUseCase.getDayInWeek()
        timeTitle.value = getTimeTitle()
//        overallStreak.value = "${res.getString(R.string.overall_streak)} ${streaks[0]} ${getTextDay(streaks[0])}"
//        bestStreak.value = "${res.getString(R.string.best_streak)} ${streaks[1]} ${getTextDay(streaks[1])}"
//        updateCheckList.value = 0

//        val dataStart = ArrayList<ItemsData>()
//        getHabitsFromDBUseCase.execute(STATUS, arrayOf("0"), application).forEach {
//            dataStart.add(ItemsData(
//                it.id, it.title,
//                "${res.getString(R.string.current)} ${it.current}",
//                "${res.getString(R.string.best)} ${it.best}"))
//        }
//        data.value = dataStart
//
//        doneHabits.value = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"), application).size
//        notDoneHabits.value = data.value?.size
//        updateLabel()
    }

//    fun markItemCompleted(context:Context, id: Int){
//        val item = getHabitsFromDBUseCase.execute(ID, arrayOf(id.toString()), context)
//        item[0].status = 1
//        item[0].date_of_week += 1
//        updateHabitUseCase.execute(item[0], context)
//
//        updateData(context,-1, item[0])
//        updateDone()
////        MAIN.vmAnalysis?.updateItem(id)
////        MAIN.vmFinish?.addItem(HabitFinishItemData(item[0].id, item[0].title))
//    }

    fun deleteItem(id: Int){
        viewModelScope.launch {
            deleteHabitUseCase(id)
        }
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

//    private fun updateLabel(){
//        label.value = "${doneHabits.value!!} ${res.getStringArray(R.array.chart_text)[0]} " +
//                "${doneHabits.value!! + data.value!!.size} ${res.getStringArray(R.array.chart_text)[1]}"
//    }

//    fun updateChart(value: Int){
//        notDoneHabits.value = notDoneHabits.value?.plus(value)
//        updateLabel()
//    }

//    fun updateDone(){
//        doneHabits.value = doneHabits.value?.plus(1)
//        updateLabel()
//    }

//    fun updateData(context: Context, action: Int, item: HabitItem?){
//        if (action == 1) {
//            val habit = GetLastHabitFromDBUseCase().execute(context)
//            data.value?.add(
//                ItemsData(habit.id, habit.title,
//                    "${res.getString(R.string.current)} ${habit.current}",
//                    "${res.getString(R.string.best)} ${habit.best}"
//                )
//            )
//            updateChart(1)
//        }else if (action == 0){
//            data.value?.forEach{
//                if(it.id == item?.id) it.NameItem = item.title
//            }
//        }else if(action == -1){
//            var itemR: ItemsData? = null
//            data.value?.forEach{
//                if(it.id == item?.id) itemR = it
//            }
//            data.value?.remove(itemR)
//        }
//        updateCheckList.value = updateCheckList.value?.plus(1)
//    }

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