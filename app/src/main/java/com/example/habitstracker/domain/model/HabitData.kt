package com.example.habitstracker.domain.model

data class HabitData(val id: Int, var title: String, var period: Int, var status: Int, var date_of_week: Int, var current: Int,
                     var best: Int, var description: String)
