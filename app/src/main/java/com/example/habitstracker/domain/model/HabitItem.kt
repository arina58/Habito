package com.example.habitstracker.domain.model

data class HabitItem(
    val id: Int = 0,
    var title: String,
    var period: Int,
    var status: Int,
    var dateOfWeek: Int,
    var current: Int,
    var best: Int,
    var description: String
)
