package com.example.habitstracker.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.habitstracker.domain.HabitRepository
import com.example.habitstracker.domain.model.HabitItem
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(application: Application) : HabitRepository {

    private val habitListDao = AppDatabase.getInstance(application).habitListDao()
    private val mapper = HabitListMapper()

    override fun getHabitList(): LiveData<List<HabitItem>> =
        MediatorLiveData<List<HabitItem>>().apply {
            addSource(habitListDao.getHabitList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }

    override suspend fun getHabitItem(habitItemId: Int): HabitItem {
        val dbModel = habitListDao.getHabitItem(habitItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun addHabitItem(habitItem: HabitItem) {
        habitListDao.addHabitItem(mapper.mapEntityToDbModel(habitItem))
    }

    override suspend fun deleteHabitItem(habitItemId: Int) {
        habitListDao.deleteHabitItem(habitItemId)
    }

    override suspend fun editHabitItem(habitItem: HabitItem) {
        habitListDao.addHabitItem(mapper.mapEntityToDbModel(habitItem))
    }

    override fun getNotCompletedHabitList(): LiveData<List<HabitItem>> =
        MediatorLiveData<List<HabitItem>>().apply {
            addSource(habitListDao.getNotCompletedHabitList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }

    override fun getCompletedHabitList(): LiveData<List<HabitItem>> =
        MediatorLiveData<List<HabitItem>>().apply {
            addSource(habitListDao.getCompletedHabitList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }
}