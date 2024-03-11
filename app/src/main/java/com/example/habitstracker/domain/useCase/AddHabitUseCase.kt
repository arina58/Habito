package com.example.habitstracker.domain.useCase

import com.example.habitstracker.MAIN
import com.example.habitstracker.data.DBManager
import com.example.habitstracker.data.model.HabitInsertViewModel
import com.example.habitstracker.domain.model.HabitViewModel

class AddHabitUseCase {
    fun execute(habit: HabitViewModel){
        val db = DBManager(MAIN)
        db.openDB()
        db.insertToDB(HabitInsertViewModel(title = habit.title, period = habit.period))
        db.closeDB()
    }
}