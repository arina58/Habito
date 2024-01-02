package com.example.habitstracker.nav_fragment

import android.app.Dialog
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
import com.example.habitstracker.domain.useCase.GetWeeklyDateUseCase
import com.example.habitstracker.domain.useCase.GetCurrentMonthUseCase
import com.example.habitstracker.domain.useCase.GetUserNameUseCase
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.eazegraph.lib.models.PieModel
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var HomeClass: FragmentHomeBinding
    private val getWeeklyDate = GetWeeklyDateUseCase()
    private val getCurrentMonth = GetCurrentMonthUseCase()
    private val getUserName = GetUserNameUseCase()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        HomeClass = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val main_coor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        val bar = MAIN.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        main_coor.visibility = View.VISIBLE
        bar.background = null
        return HomeClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setName()
        setTime()
        createCalendar()
        HomeClass.CheckList.isNestedScrollingEnabled = false
        addPieChart()
        addCheckList()
        HomeClass.ActionButton.setOnClickListener {
            showAddDialog()
        }
    }

    private fun setTime(){
        val cal: Calendar = Calendar.getInstance()
        val time = cal.get(Calendar.HOUR_OF_DAY)


        HomeClass.Hour.text = when(time){
            in 0..3 -> "Good Night"
            in 4..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..21 -> "Good Evening"
            in 22..23 -> "Good Night"
            else -> "error"
        }
    }

    private fun setName(){
        HomeClass.Hello.text = "Hello, ${getUserName.execute()}"
    }

    private fun showAddDialog() {
        val dialog = Dialog(MAIN)
        dialog.setContentView(R.layout.new_goal)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show()

        val button_create: Button = dialog.findViewById(R.id.ButtonCreate)
        button_create.setOnClickListener {
            dialog.cancel()
        }
    }

    private fun addPieChart(){
        HomeClass.PieChart.addPieSlice(
            PieModel("Integer 1", 33F, Color.parseColor("#8D4AF8")
            )
        )
        HomeClass.PieChart.addPieSlice(
            PieModel("Integer 2", 66F, Color.parseColor("#CFB1FF")
            )
        )
        HomeClass.PieChart.startAnimation()
    }

    private fun addCheckList(){
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

    private fun createCalendar(){

        val weeklyDate = getWeeklyDate.execute()
        val currentMonth = getCurrentMonth.execute()

        setColorDate(getWeeklyDate.getDayInWeek())

        HomeClass.tvDay1.text = weeklyDate[0].toString()
        HomeClass.tvDay2.text = weeklyDate[1].toString()
        HomeClass.tvDay3.text = weeklyDate[2].toString()
        HomeClass.tvDay4.text = weeklyDate[3].toString()
        HomeClass.tvDay5.text = weeklyDate[4].toString()
        HomeClass.tvDay6.text = weeklyDate[5].toString()
        HomeClass.tvDay7.text = weeklyDate[6].toString()

        HomeClass.tvMonth1.text = currentMonth
        HomeClass.tvMonth2.text = currentMonth
        HomeClass.tvMonth3.text = currentMonth
        HomeClass.tvMonth4.text = currentMonth
        HomeClass.tvMonth5.text = currentMonth
        HomeClass.tvMonth6.text = currentMonth
        HomeClass.tvMonth7.text = currentMonth
    }

    private fun setColorDate(currentDate: Int) {
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