package com.example.habitstracker.domain.model

data class HabitViewModel(val id: Int, var title: String, var period: Int, var status: Int, var date_of_week: Int, var current: Int,
                          var best: Int)
