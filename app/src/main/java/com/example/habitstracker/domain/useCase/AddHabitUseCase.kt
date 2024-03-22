package com.example.habitstracker.domain.useCase

import com.example.habitstracker.MAIN
import com.example.habitstracker.data.DBManager
import com.example.habitstracker.data.model.HabitInsertData
import com.example.habitstracker.domain.model.HabitData

class AddHabitUseCase {
    fun execute(habit: HabitData){
        val db = DBManager(MAIN)
        db.openDB()
        db.insertToDB(HabitInsertData(title = habit.title, period = habit.period, habit.description))
        db.closeDB()
    }
}