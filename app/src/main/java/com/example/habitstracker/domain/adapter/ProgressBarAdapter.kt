package com.example.habitstracker.domain.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.R
import com.example.habitstracker.domain.model.ProgressData

class ProgressBarAdapter(private var mList: List<ProgressData>) :
    RecyclerView.Adapter<ProgressBarAdapter.ViewHolder>() {

    private var clickListener: ((ProgressData) -> Unit)? = null

    fun setOnItemClickListener(listener: (ProgressData) -> Unit) {
        clickListener = listener
    }

    fun setData(data: List<ProgressData>){
        mList = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.progress_bar, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val progressViewModel = mList[position]

        holder.nameItem.text = progressViewModel.ItemName
        holder.description.text = progressViewModel.Count.toString() + progressViewModel.text
        holder.bar.max = 7
        ObjectAnimator.ofInt(holder.bar, "progress",progressViewModel.Count).setDuration(1000).start()

        holder.itemView.setOnClickListener {
            clickListener?.invoke(progressViewModel)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val nameItem: TextView = itemView.findViewById(R.id.NameItem)
        val description: TextView = itemView.findViewById(R.id.Current)
        val bar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }
}