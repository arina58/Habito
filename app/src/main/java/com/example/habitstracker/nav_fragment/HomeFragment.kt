package com.example.habitstracker.nav_fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
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
    private lateinit var homeClass: FragmentHomeBinding
    private val getWeeklyDate = GetWeeklyDateUseCase()
    private val getCurrentMonth = GetCurrentMonthUseCase()
    private val getUserName = GetUserNameUseCase()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeClass = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val mainCoor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        val bar = MAIN.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        mainCoor.visibility = View.VISIBLE
        bar.background = null
        return homeClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setName()
        setTime()
        createCalendar()
        homeClass.CheckList.isNestedScrollingEnabled = false
        addPieChart()
        addCheckList()
        homeClass.ActionButton.setOnClickListener {
            showAddDialog()
        }
    }

    private fun setTime(){
        val cal: Calendar = Calendar.getInstance()
        val time = cal.get(Calendar.HOUR_OF_DAY)


        homeClass.Hour.text = when(time){
            in 0..3 -> "Good Night"
            in 4..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..21 -> "Good Evening"
            in 22..23 -> "Good Night"
            else -> "error"
        }
    }

    private fun setName(){
        homeClass.Hello.text = "Hello, ${getUserName.execute()}"
    }

    @SuppressLint("ResourceType")
    private fun showAddDialog() {
        val dialog = Dialog(MAIN)
        dialog.setContentView(R.layout.new_goal)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        val createButton: Button = dialog.findViewById(R.id.ButtonCreate)
        createButton.setOnClickListener {
            dialog.cancel()
        }
    }

    private fun addPieChart(){
        homeClass.PieChart.addPieSlice(
            PieModel("Integer 1", 33F, Color.parseColor("#8D4AF8")
            )
        )
        homeClass.PieChart.addPieSlice(
            PieModel("Integer 2", 66F, Color.parseColor("#CFB1FF")
            )
        )
        homeClass.PieChart.startAnimation()
    }

    private fun addCheckList(){
        val recyclerview = homeClass.CheckList
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

        homeClass.tvDay1.text = weeklyDate[0].toString()
        homeClass.tvDay2.text = weeklyDate[1].toString()
        homeClass.tvDay3.text = weeklyDate[2].toString()
        homeClass.tvDay4.text = weeklyDate[3].toString()
        homeClass.tvDay5.text = weeklyDate[4].toString()
        homeClass.tvDay6.text = weeklyDate[5].toString()
        homeClass.tvDay7.text = weeklyDate[6].toString()

        homeClass.tvMonth1.text = currentMonth
        homeClass.tvMonth2.text = currentMonth
        homeClass.tvMonth3.text = currentMonth
        homeClass.tvMonth4.text = currentMonth
        homeClass.tvMonth5.text = currentMonth
        homeClass.tvMonth6.text = currentMonth
        homeClass.tvMonth7.text = currentMonth
    }

    private fun setColorDate(currentDate: Int) {
        when (currentDate) {
            0 -> {
                homeClass.tvDay1.setTextColor(Color.parseColor("#8D4AF8"))
                homeClass.tvMonth1.setTextColor(Color.parseColor("#8D4AF8"))
            }
            1 -> {
                homeClass.tvDay2.setTextColor(Color.parseColor("#8D4AF8"))
                homeClass.tvMonth2.setTextColor(Color.parseColor("#8D4AF8"))
            }
            2 -> {
                homeClass.tvDay3.setTextColor(Color.parseColor("#8D4AF8"))
                homeClass.tvMonth3.setTextColor(Color.parseColor("#8D4AF8"))
            }
            3 -> {
                homeClass.tvDay4.setTextColor(Color.parseColor("#8D4AF8"))
                homeClass.tvMonth4.setTextColor(Color.parseColor("#8D4AF8"))
            }
            4 -> {
                homeClass.tvDay5.setTextColor(Color.parseColor("#8D4AF8"))
                homeClass.tvMonth5.setTextColor(Color.parseColor("#8D4AF8"))
            }
            5 -> {
                homeClass.tvDay6.setTextColor(Color.parseColor("#8D4AF8"))
                homeClass.tvMonth6.setTextColor(Color.parseColor("#8D4AF8"))
            }
            6 -> {
                homeClass.tvDay7.setTextColor(Color.parseColor("#8D4AF8"))
                homeClass.tvMonth7.setTextColor(Color.parseColor("#8D4AF8"))
            }
        }
    }
}