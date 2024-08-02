package com.example.habitstracker.presentation.analysisFragment

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.R
import com.example.habitstracker.domain.model.HabitItem

class ProgressBarAdapter() :
    ListAdapter<HabitItem, ProgressBarAdapter.ProgressDataViewHolder>(ProgressDataDiffCallback()) {

    var clickListener: ((HabitItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressDataViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.progress_bar, parent, false)

        return ProgressDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgressDataViewHolder, position: Int) {

        val item = getItem(position)

        holder.nameItem.text = item.title
//        holder.description.text = buildString {
//            append(item.Count.toString())
//            append(item.text)
//        }

        holder.bar.max = 7
        ObjectAnimator.ofInt(holder.bar, "progress", item.current).setDuration(1000).start()

        holder.itemView.setOnClickListener {
            clickListener?.invoke(item)
        }
    }

    class ProgressDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameItem: TextView = itemView.findViewById(R.id.NameItem)
        val description: TextView = itemView.findViewById(R.id.Current)
        val bar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}