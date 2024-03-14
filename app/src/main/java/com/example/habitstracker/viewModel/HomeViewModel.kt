package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.STATUS
import com.example.habitstracker.domain.adapter.CustomAdapter
import com.example.habitstracker.domain.model.HabitViewModel
import com.example.habitstracker.domain.model.ItemsViewModel
import com.example.habitstracker.domain.useCase.*
import org.eazegraph.lib.charts.PieChart
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

    private var data =  MutableLiveData<MutableList<ItemsViewModel>>()
    private val streaks = GetStreakUseCase().execute()

    init{
        userName.value = GetUserNameUseCase().execute()
        weeklyDate.value = GetWeeklyDateUseCase().execute()
        currentMonth.value = GetCurrentMonthUseCase().execute()
        dayInWeek.value = GetWeeklyDateUseCase().getDayInWeek()
        timeTitle.value = getTimeTitle()
        overallStreak.value = "Overall streak: ${streaks[0]} days"
        bestStreak.value = "Overall best streak: ${streaks[1]} days"

        val dataStart = ArrayList<ItemsViewModel>()
        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0")).forEach {
            dataStart.add(ItemsViewModel(it.id, it.title, "Current: ${it.current}", "Best: ${it.best}"))
        }
        data.value = dataStart

        doneHabits.value = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1")).size
        notDoneHabits.value = data.value?.size
    }

    private fun updateLabel(){
        label.value = "${doneHabits.value!!} of ${doneHabits.value!! + data.value!!.size} habits"
    }

    fun updateChart(value: Int){
        notDoneHabits.value = notDoneHabits.value?.plus(value)
    }

    fun updateDone(){
        doneHabits.value = doneHabits.value?.plus(1)
    }

    fun updateData(action: Int, item: HabitViewModel?){
        val list = MAIN.findViewById<RecyclerView>(R.id.CheckList)
        val adapter = list.adapter as CustomAdapter

        if (action == 1) {
            val habit = GetLastHabitFromDBUseCase().execute()
            data.value?.add(
                ItemsViewModel(
                    habit.id,
                    habit.title,
                    "Current: ${habit.current}",
                    "Best: ${habit.best}"
                )
            )
            updateChart(1)
        }else if (action == 0){
            data.value?.forEach{
                if(it.id == item?.id){
                    it.NameItem = item.title

                }
            }
        }else if(action == -1){
            var itemR: ItemsViewModel? = null
            data.value?.forEach{
                if(it.id == item?.id){
                    itemR = it
                }
            }
            data.value?.remove(itemR)
        }

        adapter.setData(data.value!!)
        list.adapter?.notifyDataSetChanged()
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

    fun addPieChart(chart: PieChart){
        AddPieChartUseCase().execute(chart, doneHabits.value!!, data.value!!.size)

        updateLabel()
    }

     fun addCheckList(checkList: RecyclerView){
        checkList.layoutManager = LinearLayoutManager(MAIN)
        val adapter = CustomAdapter(data.value!!)
        checkList.adapter = adapter
    }
}