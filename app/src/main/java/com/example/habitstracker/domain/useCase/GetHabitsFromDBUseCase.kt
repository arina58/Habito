package com.example.habitstracker.domain.useCase

import android.content.Context
import com.example.habitstracker.data.DBManager
import com.example.habitstracker.domain.model.HabitData

class GetHabitsFromDBUseCase {
    fun execute(column: String, args: Array<String>, context: Context): ArrayList<HabitData> {
        val db = DBManager(context)
        db.openDB()
        val result = db.readData(column, args)
        db.closeDB()
        val habitsArray = ArrayList<HabitData>()
        result.forEach {
            val value = HabitData(it.id, it.title, it.period,
                it.status, it.date_of_week, it.current, it.best, it.description)
            habitsArray.add(value)
        }

        return habitsArray
    }
}