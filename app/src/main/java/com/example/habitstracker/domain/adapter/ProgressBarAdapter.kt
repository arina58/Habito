package com.example.habitstracker.domain.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.R
import com.example.habitstracker.domain.model.ProgressViewModel

class ProgressBarAdapter(private val mList: List<ProgressViewModel>) :
    RecyclerView.Adapter<ProgressBarAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.progress_bar, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ProgressViewModel = mList[position]

        holder.nameItem.text = ProgressViewModel.ItemName
        holder.Current.text = ProgressViewModel.Current
        holder.Bar.max = 7
        ObjectAnimator.ofInt(holder.Bar, "progress",ProgressViewModel.Num).setDuration(1000).start()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val nameItem: TextView = itemView.findViewById(R.id.NameItem)
        val Current: TextView = itemView.findViewById(R.id.Current)
        val Bar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}