package com.example.habitstracker.domain.useCase

import com.example.habitstracker.MAIN
import com.example.habitstracker.data.DBManager
import com.example.habitstracker.domain.model.HabitViewModel

class GetLastHabitFromDBUseCase {
    fun execute(): HabitViewModel {
        val db = DBManager(MAIN)
        db.openDB()
        val result = db.readLastRecord()
        db.closeDB()
        return HabitViewModel(result!!.id, result!!.title, result!!.period, result!!.status, result!!.date_of_week, result!!.current, result!!.best)
    }
}