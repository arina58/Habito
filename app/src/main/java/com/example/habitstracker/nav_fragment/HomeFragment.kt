package com.example.habitstracker.nav_fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitstracker.CustomAdapter
import com.example.habitstracker.ItemsViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.eazegraph.lib.models.PieModel
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    lateinit var HomeClass: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        HomeClass = FragmentHomeBinding.inflate(layoutInflater, container, false)
        var main_coor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        var bar = MAIN.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        main_coor.visibility = View.VISIBLE
        bar.background = null
        return HomeClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setName()
        setTime()
        createCalendar()
        HomeClass.CheckList.setNestedScrollingEnabled(false)
        addPieChart()
        addCheckList()
        HomeClass.ActionButton.setOnClickListener {
            showAddDialog()
        }
    }

    fun setTime(){
        val cal: Calendar = Calendar.getInstance()
        val time = cal.get(Calendar.HOUR_OF_DAY)


        HomeClass.Hour.text = when(time){
            in 4..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..21 -> "Good Evening"
            else -> ""
        }

    }

    fun setName(){
        var pref = activity?.getSharedPreferences("User", Context.MODE_PRIVATE)
        HomeClass.Hello.text = "Hello, ${pref?.getString("username", "User")}"
    }

    private fun showAddDialog() {
        val dialog = Dialog(MAIN)
        dialog.setContentView(R.layout.new_goal)
        dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show()

        val button_create: Button = dialog.findViewById(R.id.ButtonCreate)
        button_create.setOnClickListener {
            dialog.cancel()
        }
    }

    fun addPieChart(){
        HomeClass.PieChart.addPieSlice(
            PieModel("Integer 1", 33F, Color.parseColor("#8D4AF8")
            )
        )
        HomeClass.PieChart.addPieSlice(
            PieModel("Integer 2", 66F, Color.parseColor("#E7D8FF")
            )
        )
        HomeClass.PieChart.startAnimation()
    }

    fun addCheckList(){
        val recyclerview = HomeClass.CheckList
        recyclerview.layoutManager = LinearLayoutManager(MAIN)
        val data = ArrayList<ItemsViewModel>()


        data.add(ItemsViewModel("Meditation", "Current: 7", "Best: 11"))
        data.add(ItemsViewModel("Books", "Current: 2", "Best: 11"))
        data.add(ItemsViewModel("Running", "Current: 8", "Best: 11"))
        data.add(ItemsViewModel("Running", "Current: 8", "Best: 11"))
        data.add(ItemsViewModel("Running", "Current: 8", "Best: 11"))



        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter
    }

    fun createCalendar(){
        val cal: Calendar = Calendar.getInstance()
        val dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK)
        var sub = 0;

        when(dayOfWeek){
            Calendar.MONDAY -> {HomeClass.tvDay1.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth1.setTextColor(Color.parseColor("#8D4AF8"))}
            Calendar.TUESDAY -> {sub = 1
                HomeClass.tvDay2.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth2.setTextColor(Color.parseColor("#8D4AF8"))};
            Calendar.WEDNESDAY -> {sub = 2
                HomeClass.tvDay3.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth3.setTextColor(Color.parseColor("#8D4AF8"))}
            Calendar.THURSDAY -> {sub = 3
                HomeClass.tvDay4.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth4.setTextColor(Color.parseColor("#8D4AF8"))};
            Calendar.FRIDAY -> {sub = 4
                HomeClass.tvDay5.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth5.setTextColor(Color.parseColor("#8D4AF8"))};
            Calendar.SATURDAY -> {sub = 5
                HomeClass.tvDay6.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth6.setTextColor(Color.parseColor("#8D4AF8"))};
            Calendar.SUNDAY -> {sub = 6
                HomeClass.tvDay7.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth7.setTextColor(Color.parseColor("#8D4AF8"))};
        }
        val date = cal.get(Calendar.DATE)
        val month = when(cal.get(Calendar.MONTH)){
            1 -> "Jan"
            2 -> "Feb"
            3 -> "Mat"
            4 -> "Apr"
            5 -> "May"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Aug"
            9 -> "Sep"
            10 -> "Oct"
            11 -> "Nov"
            12 -> "Dec"
            else -> ""
        }

        HomeClass.tvDay1.text = (date - sub).toString()
        HomeClass.tvDay2.text = (date - sub + 1).toString()
        HomeClass.tvDay3.text = (date - sub + 2).toString()
        HomeClass.tvDay4.text = (date - sub + 3).toString()
        HomeClass.tvDay5.text = (date - sub + 4).toString()
        HomeClass.tvDay6.text = (date - sub + 5).toString()
        HomeClass.tvDay7.text = (date - sub + 6).toString()

        HomeClass.tvMonth1.text = month
        HomeClass.tvMonth2.text = month
        HomeClass.tvMonth3.text = month
        HomeClass.tvMonth4.text = month
        HomeClass.tvMonth5.text = month
        HomeClass.tvMonth6.text = month
        HomeClass.tvMonth7.text = month
    }
}