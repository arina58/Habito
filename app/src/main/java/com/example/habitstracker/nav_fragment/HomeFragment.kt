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
import com.example.habitstracker.domain.adapter.CustomAdapter
import com.example.habitstracker.domain.model.ItemsViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentHomeBinding
import com.example.habitstracker.domain.useCase.GetCurrentDateUseCase
import com.example.habitstracker.domain.useCase.GetCurrentMonthUseCase
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.eazegraph.lib.models.PieModel
import java.util.*


class HomeFragment : Fragment() {
    lateinit var HomeClass: FragmentHomeBinding
    private val getCurrentDate = GetCurrentDateUseCase()
    private  val getCurrentMonth = GetCurrentMonthUseCase()
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
        val date = cal.get(Calendar.DATE)

        val currentDate = getCurrentDate.execute()
        val currentMonth = getCurrentMonth.execute()

        setColorDate(currentDate)

        HomeClass.tvDay1.text = (date - currentDate).toString()
        HomeClass.tvDay2.text = (date - currentDate + 1).toString()
        HomeClass.tvDay3.text = (date - currentDate + 2).toString()
        HomeClass.tvDay4.text = (date - currentDate + 3).toString()
        HomeClass.tvDay5.text = (date - currentDate + 4).toString()
        HomeClass.tvDay6.text = (date - currentDate + 5).toString()
        HomeClass.tvDay7.text = (date - currentDate + 6).toString()

        HomeClass.tvMonth1.text = currentMonth
        HomeClass.tvMonth2.text = currentMonth
        HomeClass.tvMonth3.text = currentMonth
        HomeClass.tvMonth4.text = currentMonth
        HomeClass.tvMonth5.text = currentMonth
        HomeClass.tvMonth6.text = currentMonth
        HomeClass.tvMonth7.text = currentMonth
    }

    fun setColorDate(currentDate: Int) {
        when (currentDate) {
            0 -> {
                HomeClass.tvDay1.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth1.setTextColor(Color.parseColor("#8D4AF8"))
            }
            1 -> {
                HomeClass.tvDay2.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth2.setTextColor(Color.parseColor("#8D4AF8"))
            }
            2 -> {
                HomeClass.tvDay3.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth3.setTextColor(Color.parseColor("#8D4AF8"))
            }
            3 -> {
                HomeClass.tvDay4.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth4.setTextColor(Color.parseColor("#8D4AF8"))
            }
            4 -> {
                HomeClass.tvDay5.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth5.setTextColor(Color.parseColor("#8D4AF8"))
            }
            5 -> {
                HomeClass.tvDay6.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth6.setTextColor(Color.parseColor("#8D4AF8"))
            }
            6 -> {
                HomeClass.tvDay7.setTextColor(Color.parseColor("#8D4AF8"))
                HomeClass.tvMonth7.setTextColor(Color.parseColor("#8D4AF8"))
            }
        }
    }
}