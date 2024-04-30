package com.example.habitstracker.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.habitstracker.*
import com.example.habitstracker.domain.dialogs.DialogFinishHabit
import com.example.habitstracker.domain.useCase.*

class MainViewModel: ViewModel() {
    var startDestination = MutableLiveData<Int>()
    var theme = MutableLiveData<Int>()
    init{
        SetNotificationUseCase().execute()
        SetMidnightProgressUseCase().execute()
        SwitchThemeUseCase().execute(GetNameThemeUseCase().execute())
        checkFinishedHabits()
        setStartDestination()
    }

    private fun setStartDestination(){
        startDestination.value = if (GetUserNameUseCase().execute() != DEFAULT_NAME)
            R.id.homeFragment else R.id.enterNameFragment
    }

    private fun checkFinishedHabits(){
        val habits = GetHabitsFromDBUseCase().execute(PERIOD, arrayOf("0"), MAIN)
        habits.forEach {
            showFinishDialog(it.id)
        }
    }

    private fun showFinishDialog(id: Int){
        val dialog = DialogFinishHabit.newInstance(id)
        dialog.show(MAIN.supportFragmentManager, "dialog")
    }
}