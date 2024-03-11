package com.example.habitstracker.domain.model

data class HabitViewModel(val id: Int, val title: String, val period: Int, var status: Int, val date_of_week: Int, val current: Int,
                          val best: Int)
