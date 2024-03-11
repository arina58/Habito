package com.example.habitstracker.domain.useCase

import com.example.habitstracker.MAIN
import com.example.habitstracker.data.DBManager
import com.example.habitstracker.domain.model.HabitViewModel

class GetHabitsFromDBUseCase {
    fun execute(column: String, args: Array<String>): ArrayList<HabitViewModel> {
        val db = DBManager(MAIN)
        db.openDB()
        val result = db.readData(column, args)
        db.closeDB()
        val habitsArray = ArrayList<HabitViewModel>()
        result.forEach {
            val value = HabitViewModel(it.id, it.title, it.period, it.status, it.date_of_week, it.current, it.best)
            habitsArray.add(value)
        }
        return habitsArray
    }
}