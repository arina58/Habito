package com.example.habitstracker.data

import com.example.habitstracker.domain.model.HabitItem

class HabitListMapper {

    fun mapEntityToDbModel(habitItem: HabitItem): HabitItemDbModel{
        return HabitItemDbModel(
            id = habitItem.id,
            title = habitItem.title,
            period = habitItem.period,
            status = habitItem.status,
            date_of_week = habitItem.date_of_week,
            current = habitItem.current,
            best = habitItem.best,
            description = habitItem.description
        )
    }

    fun mapDbModelToEntity(habitItemDbModel: HabitItemDbModel): HabitItem{
        return HabitItem(
            id = habitItemDbModel.id,
            title = habitItemDbModel.title,
            period = habitItemDbModel.period,
            status = habitItemDbModel.status,
            date_of_week = habitItemDbModel.date_of_week,
            current = habitItemDbModel.current,
            best = habitItemDbModel.best,
            description = habitItemDbModel.description
        )
    }

    fun mapListDbModelToListEntity(list: List<HabitItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

}