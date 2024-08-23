package com.example.habitstracker.presentation.mainActivity

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.*
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.*
import com.example.habitstracker.presentation.finishHabit.FinishHabit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    getHabitsFromDBUseCase: GetHabitListUseCase,
    setNotificationUseCase: SetNotificationUseCase,
    setMidnightProgressUseCase: SetMidnightProgressUseCase,
    switchThemeUseCase: SwitchThemeUseCase,
    getNameThemeUseCase: GetNameThemeUseCase
) : ViewModel() {
    var startDestination = MutableLiveData<Int>()
    var theme = MutableLiveData<Int>()

    var data = getHabitsFromDBUseCase.invoke().value
    private val _finishedHabits = MutableLiveData<List<HabitItem>>()
    val finishedHabits: LiveData<List<HabitItem>>
        get() = _finishedHabits

    init {
        setNotificationUseCase()
        setMidnightProgressUseCase()
        switchThemeUseCase(getNameThemeUseCase())
        getFinishedHabits()
        setStartDestination()
    }

    private fun setStartDestination() {
        startDestination.value = if (getUserNameUseCase() != DEFAULT_NAME)
            R.id.homeFragment else R.id.enterNameFragment
    }

    private fun getFinishedHabits() {
        val finishedHabitsArr = mutableListOf<HabitItem>()

        data?.forEach {
            if (it.period == 0) finishedHabitsArr.add(it)
        }

        _finishedHabits.value = finishedHabitsArr.toList()
    }

    fun showFinishedHabits(fragmentManager: FragmentManager) {
        finishedHabits.value?.forEach {
            val dialog = FinishHabit.newInstance(it.id)
            dialog.show(fragmentManager, "dialog")
        }
    }
}