package com.example.habitstracker.domain.useCase

import android.content.ContentValues
import android.content.Context
import com.example.habitstracker.*
import com.example.habitstracker.data.DBManager
import com.example.habitstracker.domain.model.HabitData

class UpdateHabitUseCase {
    fun execute(item: HabitData, context: Context){
        val value = ContentValues().apply {
            put(TITLE, item.title)
            put(PERIOD, item.period)
            put(STATUS, item.status)
            put(DAYS_OF_WEEK, item.date_of_week)
            put(CURRENT, item.current)
            put(BEST, item.best)
            put(DESCRIPTION, item.description)
        }

        val db = DBManager(context)
        db.openDB()
        db.updateData(item.id, value)
        db.closeDB()
    }
}