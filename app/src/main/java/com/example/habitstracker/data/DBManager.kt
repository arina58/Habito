package com.example.habitstracker.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.habitstracker.data.model.HabitGetViewModel
import com.example.habitstracker.data.model.HabitInsertViewModel

class DBManager(context: Context) {
    private val myDBHelper = DBHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDB(){
        db = myDBHelper.writableDatabase
    }

    fun insertToDB(habit: HabitInsertViewModel){
        val values = ContentValues().apply {
            put(DBObject.COLUMN_NAME_TITLE, habit.title)
            put(DBObject.COLUMN_NAME_PERIOD, habit.period)
            put(DBObject.COLUMN_NAME_STATUS, 0)
            put(DBObject.COLUMN_NAME_DAYS_OF_WEEK, 0)
            put(DBObject.COLUMN_NAME_CURRENT, 0)
            put(DBObject.COLUMN_NAME_BEST, 0)
        }

        db?.insert(DBObject.TABLE_NAME, null, values)

    }

    @SuppressLint("Range")
    fun readData(column: String, args: Array<String>): ArrayList<HabitGetViewModel>{
        var result = ArrayList<HabitGetViewModel>()

        val cursor = db?.query(DBObject.TABLE_NAME, null, "$column = ?", args, null, null, null)

        while (cursor?.moveToNext()!!){
            val id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val title = cursor.getString(cursor.getColumnIndex(DBObject.COLUMN_NAME_TITLE))
            val period = cursor.getInt(cursor.getColumnIndex(DBObject.COLUMN_NAME_PERIOD))
            val status = cursor.getInt(cursor.getColumnIndex(DBObject.COLUMN_NAME_STATUS))
            val dayOfWeek = cursor.getInt(cursor.getColumnIndex(DBObject.COLUMN_NAME_DAYS_OF_WEEK))
            val current = cursor.getInt(cursor.getColumnIndex(DBObject.COLUMN_NAME_CURRENT))
            val best = cursor.getInt(cursor.getColumnIndex(DBObject.COLUMN_NAME_BEST))

            val user = HabitGetViewModel(id, title, period, status, dayOfWeek, current, best)
            result.add(user)
        }
        cursor.close()
        return result
    }

    fun updateData(id: Int, value: ContentValues){
        db?.update(DBObject.TABLE_NAME, value, BaseColumns._ID + "=" + id, null)
    }

    fun closeDB(){
        myDBHelper.close()
    }

    fun deleteItem(itemId: Int) {
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(itemId.toString())
        db?.delete(DBObject.TABLE_NAME, selection, selectionArgs)
    }
}