package com.example.habitstracker.domain.useCase

import com.example.habitstracker.MAIN
import com.example.habitstracker.data.DBManager

class DeleteHabitUseCase {
    fun execute(id: Int){
        val db = DBManager(MAIN)
        db.openDB()
        db.deleteItem(id)
        db.closeDB()
    }
}