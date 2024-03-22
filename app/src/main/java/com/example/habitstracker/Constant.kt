package com.example.habitstracker

import android.provider.BaseColumns

lateinit var MAIN: MainActivity
const val USER_NAME = "username"
const val DEFAULT_NAME = "default"
const val DEFAULT_LOCATION = false
const val DEFAULT_RECEIVE = true
const val RECEIVE_NOTIFICATION = "receive_notifications"
const val NAME_THEME = "Name_theme"
const val NAV_LOCATION = "nav_location"
const val DARK_THEME = false
const val LIGHT_THEME = true

const val ID = "${BaseColumns._ID}"
const val TITLE = "title"
const val PERIOD = "period"
const val STATUS = "status"
const val DAYS_OF_WEEK = "days_of_week"
const val CURRENT = "current"
const val BEST = "best"
const val DESCRIPTION = "description"

const val CURRENT_STREAK = "currentStreak"
const val BEST_STREAK = "bestStreak"