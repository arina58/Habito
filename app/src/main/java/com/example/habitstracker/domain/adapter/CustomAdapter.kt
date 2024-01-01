package com.example.habitstracker.domain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.R
import com.example.habitstracker.domain.model.ItemsViewModel

class CustomAdapter(private val mList: List<ItemsViewModel>) :  RecyclerView.Adapter<CustomAdapter.ViewHolder>(),
    View.OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_of_list, parent, false)

        val holder = ViewHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.nameItem.text = ItemsViewModel.NameItem
        holder.Current.text = ItemsViewModel.Current
        holder.Best.text = ItemsViewModel.Best

        holder.more.setOnClickListener(this)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val nameItem: TextView = itemView.findViewById(R.id.NameItem)
        val Current: TextView = itemView.findViewById(R.id.Current)
        val Best: TextView = itemView.findViewById(R.id.Best)
        val more = itemView.findViewById<ImageButton>(R.id.moreButton)


    }

    override fun onClick(v: View) {
        showPopupMenu(v)
    }

    fun showPopupMenu(v: View){
       var popupMenu: PopupMenu = PopupMenu(v.context, v)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.show()
    }
}