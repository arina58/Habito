package com.example.habitstracker.presentation.finishFragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.example.habitstracker.databinding.FragmentFinishHabitsBinding
import com.example.habitstracker.domain.model.HabitItem


class FinishHabitsAdapter :
    ListAdapter<HabitItem, FinishHabitsAdapter.FinishHabitViewHolder>(
        FinishItemDiffCallback()
    ) {

    var clickListener: ((id: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishHabitViewHolder {

        val view = FragmentFinishHabitsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FinishHabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: FinishHabitViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item.title

        holder.returnButton.setOnClickListener {
            clickListener?.invoke(item.id)
        }
    }


    class FinishHabitViewHolder(binding: FragmentFinishHabitsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.title
        val returnButton: ImageButton = binding.returnButton
    }
}