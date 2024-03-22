package com.example.habitstracker.data

import android.provider.BaseColumns

object DBObject : BaseColumns {
    const val DATABASE_VERSION = 2
    const val DATABASE_NAME = "HabitsData.db"
    const val TABLE_NAME = "Habits"

    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_PERIOD = "period"
    const val COLUMN_NAME_STATUS = "status"
    const val COLUMN_NAME_DAYS_OF_WEEK = "days_of_week"
    const val COLUMN_NAME_CURRENT = "current"
    const val COLUMN_NAME_BEST = "best"
    const val COLUMN_DESCRIPTION = "description"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT," +
            "$COLUMN_NAME_PERIOD INTEGER,$COLUMN_NAME_STATUS INTEGER,$COLUMN_NAME_DAYS_OF_WEEK INTEGER," +
            "$COLUMN_NAME_CURRENT INTEGER,$COLUMN_NAME_BEST INTEGER,$COLUMN_DESCRIPTION TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}