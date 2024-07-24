package com.example.habitstracker.presentation.analysisFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.habitstracker.domain.model.HabitItem

class ProgressDataDiffCallback: DiffUtil.ItemCallback<HabitItem>() {
    override fun areItemsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
        return oldItem == newItem
    }
}