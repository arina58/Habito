package com.example.habitstracker.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.habitstracker.*
import com.example.habitstracker.domain.dialogs.DialogFinishHabit
import com.example.habitstracker.domain.useCase.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar

class MainViewModel: ViewModel() {
    init{
        checkDate()
        checkFinishedHabits()
    }

    private fun checkDate(){
        if(GetLastDateUseCase().execute() < Calendar.DAY_OF_MONTH){
            SaveCurrentDateUseCase().execute(Calendar.DAY_OF_MONTH)
            ProgressHabitsUseCase().execute()
        }
    }

    fun setNavController(navController: NavController, bottomNavigationView: BottomNavigationView){
        var startDestination = if (GetUserNameUseCase().execute() != DEFAULT_NAME) R.id.homeFragment else R.id.enterNameFragment

        if(startDestination == R.id.homeFragment){
            if(GetNavLocationUseCase().execute()) startDestination = R.id.settingsFragment
            SaveNavLocationUseCase().execute(false)
        }

        (MAIN.supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).also { navHost ->
            val navInflater = navHost.navController.navInflater
            val navGraph = navInflater.inflate(R.navigation.nav_graph).apply {
                setStartDestination(startDestination)
            }
            navHost.navController.graph = navGraph
        }

        bottomNavigationView.setupWithNavController(navController)

        SwitchThemeUseCase().execute(GetNameThemeUseCase().execute())
    }

    private fun checkFinishedHabits(){
        val habits = GetHabitsFromDBUseCase().execute(PERIOD, arrayOf("0"))
        habits.forEach {
            showFinishDialog(it.id)
        }
    }

    private fun showFinishDialog(id: Int){
        val dialog = DialogFinishHabit.newInstance(id)
        dialog.show(MAIN.supportFragmentManager, "dialog")
    }
}