package com.example.habitstracker.domain.adapter

import android.app.Dialog
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.ID
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.STATUS
import com.example.habitstracker.domain.model.ItemsViewModel
import com.example.habitstracker.domain.useCase.AddPieChartUseCase
import com.example.habitstracker.domain.useCase.DeleteHabitUseCase
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import org.eazegraph.lib.charts.PieChart

class CustomAdapter(var mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_of_list, parent, false)
        return ViewHolder(view)
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
            markCompleted(position, mList[position].id)
        }
    }
    private fun markCompleted(position: Int, id: Int) {
        val item = GetHabitsFromDBUseCase().execute(ID, arrayOf(id.toString()))
        item[0].status = 1
        UpdateHabitUseCase().execute(item[0])

        val mutableList = mList.toMutableList()
        mutableList.removeAt(position)
        mList = mutableList.toList()
        notifyItemRemoved(position)
        if (position != mList.size) {
            notifyItemRangeChanged(position, mList.size - position)
        }

        val done = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"))
        val undone = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"))
        val pieChart = MAIN.findViewById<PieChart>(R.id.PieChart)
        AddPieChartUseCase().execute(pieChart, done.size, undone.size)
        val descDone = MAIN.findViewById<TextView>(R.id.DescDone)

        descDone.text = "${done.size} of ${done.size + undone.size} habits"
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
                    showChangeDialog(mList[position].id, position)
                    true
                }
                R.id.delete -> {
                    DeleteHabitUseCase().execute(mList[position].id)

                    val mutableList = mList.toMutableList()
                    mutableList.removeAt(position)

                    mList = mutableList.toList()
                    notifyItemRemoved(position)
                    if (position != mList.size) {
                        notifyItemRangeChanged(position, mList.size - position)
                    }

                    val done = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"))
                    val undone = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"))
                    val pieChart = MAIN.findViewById<PieChart>(R.id.PieChart)
                    AddPieChartUseCase().execute(pieChart, done.size, undone.size)
                    val descDone = MAIN.findViewById<TextView>(R.id.DescDone)

                    descDone.text = "${done.size} of ${done.size + undone.size} habits"
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun showChangeDialog(id: Int, position: Int) {
        val dialog = Dialog(MAIN)
        dialog.setContentView(R.layout.change_goal)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(true)
        dialog.show()

        val item = GetHabitsFromDBUseCase().execute(ID, arrayOf(id.toString()))

        val title = dialog.findViewById<EditText>(R.id.NameGoal)
        title.text = Editable.Factory.getInstance().newEditable(item[0].title)
        val period = dialog.findViewById<EditText>(R.id.editTextNumber)
        period.text = Editable.Factory.getInstance().newEditable(item[0].period.toString())

        val createButton: Button = dialog.findViewById(R.id.ButtonCreate)
        createButton.setOnClickListener {
            if(title.text.length in 1..255 && period.text.toString().toInt() in 2..365) {
                UpdateHabitUseCase().execute(item[0])
                dialog.cancel()

                val mutableList = mList.toMutableList()
                mutableList[position].NameItem = title.text.toString()
                notifyItemChanged(position)
            }
        }
    }
}