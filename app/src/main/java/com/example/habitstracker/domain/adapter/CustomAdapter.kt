package com.example.habitstracker.domain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.ID
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.domain.dialogs.DialogChangeHabit
import com.example.habitstracker.domain.model.HabitFinishItemData
import com.example.habitstracker.domain.model.ItemsData
import com.example.habitstracker.domain.useCase.DeleteHabitUseCase
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase

class CustomAdapter(private var mList: List<ItemsData>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_of_list, parent, false)
        return ViewHolder(view)
    }

    fun setData(data: List<ItemsData>){
        mList = data
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel = mList[position]

        holder.nameItem.text = itemViewModel.NameItem
        holder.current.text = itemViewModel.Current
        holder.best.text = itemViewModel.Best

        holder.more.setOnClickListener {
            showPopupMenu(holder.more, position)
        }

        holder.checkBox.setOnClickListener {
            markCompleted(mList[position].id)
            holder.checkBox.isChecked = false
        }
    }
    private fun markCompleted(id: Int) {
        val item = GetHabitsFromDBUseCase().execute(ID, arrayOf(id.toString()), MAIN)
        item[0].status = 1
        item[0].date_of_week += 1
        UpdateHabitUseCase().execute(item[0], MAIN)

        MAIN.vmHome.updateData(-1, item[0])
        MAIN.vmHome.updateDone()
        MAIN.vmAnalysis?.updateItem(id)
        MAIN.vmFinish?.addItem(HabitFinishItemData(item[0].id, item[0].title))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameItem: TextView = itemView.findViewById(R.id.NameItem)
        val current: TextView = itemView.findViewById(R.id.Current)
        val best: TextView = itemView.findViewById(R.id.Best)
        val more: ImageButton = itemView.findViewById(R.id.moreButton)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }

    private fun showPopupMenu(view: View, position: Int) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.popup_menu)

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.change -> {
                    val dialog = DialogChangeHabit.newInstance(mList[position].id)
                    dialog.show(MAIN.supportFragmentManager, "dialogChange")
                    true
                }
                R.id.delete -> {
                    val item = GetHabitsFromDBUseCase().execute(ID, arrayOf("${mList[position].id}"), MAIN)
                    DeleteHabitUseCase().execute(mList[position].id)
                    MAIN.vmHome.updateData(-1, item[0])

                    MAIN.vmHome.updateChart(-1)
                    MAIN.vmAnalysis?.deleteItem(item[0].id)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}
