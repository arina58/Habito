package com.example.habitstracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var period: Int,
    var status: Int,
    var date_of_week: Int,
    var current: Int,
    var best: Int,
    var description: String
)
