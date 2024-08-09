package com.example.habitstracker.presentation.homeFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.R
import com.example.habitstracker.domain.model.HabitItem

class CustomAdapter: ListAdapter<HabitItem, CustomAdapter.ItemsDataViewHolder>(
    ItemsDataDiffCallback()
) {

    var checkBoxListener: ((id: Int) -> Unit)? = null
    var deleteItemListener: ((id: Int) -> Unit)? = null
    var changeItemListener: ((id: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_of_list, parent, false)
        return ItemsDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsDataViewHolder, position: Int) {
        val item = getItem(position)

        holder.nameItem.text = item.title
        holder.current.text = item.current.toString()
        holder.best.text = item.best.toString()

        holder.more.setOnClickListener {
            showPopupMenu(holder.more, position)
        }

        holder.checkBox.setOnClickListener {
            markCompleted(item.id)
            holder.checkBox.isChecked = false
        }


    }
    private fun markCompleted(id: Int) {
        checkBoxListener?.invoke(id)
    }

    class ItemsDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                    changeItemListener?.invoke(getItem(position).id)
                    true
                }
                R.id.delete -> {
                    deleteItemListener?.invoke(getItem(position).id)
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}
