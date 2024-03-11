package com.example.habitstracker.domain.useCase

import android.content.ContentValues
import com.example.habitstracker.*
import com.example.habitstracker.data.DBManager
import com.example.habitstracker.domain.model.HabitViewModel

class UpdateHabitUseCase {
    fun execute(item: HabitViewModel){
        val value = ContentValues().apply {
            put(TITLE, item.title)
            put(PERIOD, item.period)
            put(STATUS, item.status)
            put(DAYS_OF_WEEK, item.date_of_week)
            put(CURRENT, item.current)
            put(BEST, item.best)
        }

        val db = DBManager(MAIN)
        db.openDB()
        db.updateData(item.id, value)
        db.closeDB()
    }
}