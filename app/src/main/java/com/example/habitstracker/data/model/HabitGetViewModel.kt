package com.example.habitstracker.data.model

data class HabitGetViewModel(val id: Int, val title: String, val period: Int, val status: Int,
                             val date_of_week: Int, val current: Int, val best: Int)
