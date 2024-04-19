package com.example.habitstracker.domain.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.habitstracker.ID
import com.example.habitstracker.MAIN
import com.example.habitstracker.databinding.FragmentFinishHabitsBinding
import com.example.habitstracker.domain.model.HabitFinishItemData
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase

class FinishHabitsAdapter(private var mList: List<HabitFinishItemData>) : RecyclerView.Adapter<FinishHabitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentFinishHabitsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.title.text = item.title

        holder.returnButton.setOnClickListener {
            returnHabit(mList[position].id, position)
        }
    }

    private fun returnHabit(id: Int, position: Int){
        val item = GetHabitsFromDBUseCase().execute(ID, arrayOf(id.toString()), MAIN)
        item[0].status = 0
        item[0].date_of_week -= 1
        UpdateHabitUseCase().execute(item[0], MAIN)

        MAIN.vmFinish?.deleteItem(item[0].id)
        MAIN.vmAnalysis?.updateItem(id)
    }

    override fun getItemCount(): Int = mList.size

     class ViewHolder(binding: FragmentFinishHabitsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.title
        val returnButton: ImageButton = binding.returnButton
    }

    fun setData(data: List<HabitFinishItemData>){
        mList = data
    }
}