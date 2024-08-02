package com.example.habitstracker.presentation.mainActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habitstracker.*
import com.example.habitstracker.data.HabitRepositoryImpl
import com.example.habitstracker.domain.useCase.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getHabitsFromDBUseCase: GetHabitsFromDBUseCase,
    private val setNotificationUseCase: SetNotificationUseCase,
    private val setMidnightProgressUseCase: SetMidnightProgressUseCase,
    private val switchThemeUseCase: SwitchThemeUseCase
): AndroidViewModel(application) {
    var startDestination = MutableLiveData<Int>()
    var theme = MutableLiveData<Int>()

    var data =  getHabitsFromDBUseCase.invoke().value

    init{
//        SetNotificationUseCase().execute()
//        SetMidnightProgressUseCase().execute()
//        SwitchThemeUseCase().execute(GetNameThemeUseCase().execute())
        checkFinishedHabits()
        setStartDestination()
    }

    private fun setStartDestination(){
        startDestination.value = if (getUserNameUseCase(getApplication()) != DEFAULT_NAME)
            R.id.homeFragment else R.id.enterNameFragment
    }

    private fun checkFinishedHabits(){

        data?.forEach {
//            showFinishDialog(it.id)
        }
    }

//    private fun showFinishDialog(id: Int){
//        val dialog = DialogFinishHabit.newInstance(id)
//        dialog.show(getApplication().supportFragmentManager, "dialog")
//    }
}