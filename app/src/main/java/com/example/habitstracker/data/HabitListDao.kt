package com.example.habitstracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HabitListDao {

    @Query("SELECT * FROM habits")
    fun getHabitList(): LiveData<List<HabitItemDbModel>>

    @Query("SELECT * FROM habits WHERE id=:habitItemId LIMIT 1")
    suspend fun getHabitItem(habitItemId: Int): HabitItemDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHabitItem(habitItemDbModel: HabitItemDbModel)

    @Query("DELETE FROM habits WHERE id=:habitItemId")
    suspend fun deleteHabitItem(habitItemId: Int)

    @Query("SELECT * FROM habits WHERE status = 0")
    fun getNotCompletedHabitList(): LiveData<List<HabitItemDbModel>>

    @Query("SELECT * FROM habits WHERE status = 1")
    fun getCompletedHabitList(): LiveData<List<HabitItemDbModel>>
}