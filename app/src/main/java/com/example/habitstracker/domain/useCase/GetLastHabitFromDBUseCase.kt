package com.example.habitstracker.domain.useCase

import com.example.habitstracker.MAIN
import com.example.habitstracker.data.DBManager
import com.example.habitstracker.domain.model.HabitData

class GetLastHabitFromDBUseCase {
    fun execute(): HabitData {
        val db = DBManager(MAIN)
        db.openDB()
        val result = db.readLastRecord()
        db.closeDB()
        var habit = HabitData(-1, "", 0, 0, 0, 0, 0, "")

        result?.let { habit = HabitData(it.id, result.title, result.period, result.status,
                    result.date_of_week, result.current, result.best, result.description) }
        return habit
    }
}