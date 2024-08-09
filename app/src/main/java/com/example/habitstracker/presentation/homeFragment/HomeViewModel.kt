package com.example.habitstracker.presentation.homeFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habitstracker.R
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    application: Application,
    private val getStreakUseCase: GetStreakUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getCurrentMonthUseCase: GetCurrentMonthUseCase,
    private val getWeeklyDateUseCase: GetWeeklyDateUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val getHabitItem: GetHabitItemUseCase,
    private val getNotCompletedHabitListCase: GetNotCompletedHabitListUseCase,
    private val getCompletedHabitListUseCase: GetCompletedHabitListUseCase
) : AndroidViewModel(application) {

    private val streaks = getStreakUseCase(application)
    private val res = application.resources

    var userName = "${res.getString(R.string.hello)} ${getUserNameUseCase(application)}"
    var currentMonth = getCurrentMonthUseCase(application)
    var dayInWeek = getWeeklyDateUseCase.getDayInWeek()
    var weeklyDate = getWeeklyDateUseCase.execute()
    var timeTitle = MutableLiveData<String>()
    var overallStreak =
        "${res.getString(R.string.overall_streak)} ${streaks[0]} ${getTextDay(streaks[0])}"
    var bestStreak =
        "${res.getString(R.string.best_streak)} ${streaks[1]} ${getTextDay(streaks[1])}"

    var notCompletedHabits = getNotCompletedHabitListCase.invoke()
    private val completedHabits = getCompletedHabitListUseCase.invoke()

    var completedHabitsCount: LiveData<Int> = MediatorLiveData<Int>().apply {
        addSource(completedHabits) {
            value = it.size
        }
    }

    var notCompletedHabitsCount: LiveData<Int> = MediatorLiveData<Int>().apply {
        addSource(notCompletedHabits) {
            value = it.size
        }
    }



    var label = MutableLiveData<String>()

    init {
        timeTitle.value = getTimeTitle()
    }

    fun markItemCompleted(id: Int) {
        viewModelScope.launch {
            val habit = getHabitItem(id)
            updateHabitUseCase(
                habit.copy(
                    status = 1,
                    date_of_week = habit.date_of_week + 1
                )
            )
        }
    }

    fun deleteItem(id: Int) {
        viewModelScope.launch {
            deleteHabitUseCase(id)
        }
    }

    private fun getTextDay(day: Int): String {
        return when (day % 10) {
            0 -> res.getStringArray(R.array.days)[2]
            1 -> res.getStringArray(R.array.days)[0]
            in 2..4 -> res.getStringArray(R.array.days)[1]
            in 5..9 -> res.getStringArray(R.array.days)[2]
            else -> ""
        }
    }

    fun updateLabel() {
        val completedHabitsCountI = completedHabits.value?.size ?: 0
        val notCompletedHabitsCountI = notCompletedHabits.value?.size ?: 0
        label.value = "$completedHabitsCountI ${res.getStringArray(R.array.chart_text)[0]} " +
                "${completedHabitsCountI + notCompletedHabitsCountI} ${res.getStringArray(R.array.chart_text)[1]}"
    }

    private fun getTimeTitle(): String {
        val cal: Calendar = Calendar.getInstance()
        val text = res.getStringArray(R.array.time_text)
        return when (cal.get(Calendar.HOUR_OF_DAY)) {
            in 0..3 -> text[0]
            in 4..11 -> text[1]
            in 12..15 -> text[2]
            in 16..21 -> text[3]
            in 22..23 -> text[0]
            else -> ""
        }
    }
}