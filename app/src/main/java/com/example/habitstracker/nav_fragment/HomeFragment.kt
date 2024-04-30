package com.example.habitstracker.nav_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitstracker.*
import com.example.habitstracker.databinding.FragmentHomeBinding
import com.example.habitstracker.domain.adapter.CustomAdapter
import com.example.habitstracker.domain.dialogs.DialogAddHabit
import com.example.habitstracker.domain.useCase.AddPieChartUseCase
import com.example.habitstracker.viewModel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment(){
    private lateinit var homeClass: FragmentHomeBinding
    private lateinit var vm : HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeClass = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val mainCoordination = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        val bar = MAIN.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        mainCoordination.visibility = View.VISIBLE
        bar.background = null
        return homeClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeClass.CheckList.isNestedScrollingEnabled = false
        MAIN.vmHome = ViewModelProvider(this)[HomeViewModel::class.java]
        vm = MAIN.vmHome

        homeClass.ChartText3.text = MAIN.resources.getStringArray(R.array.chart_text)[2]

        setMainParam()
        createCalendar()
        addPieChart()
        addCheckList()

        homeClass.ActionButton.setOnClickListener {
            DialogAddHabit().show(MAIN.supportFragmentManager, "dialogAdd")
        }
        homeClass.FinishButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_homeFragment_to_finishHabitsFragment)
        }
    }

    private fun addCheckList(){
        homeClass.CheckList.layoutManager = LinearLayoutManager(MAIN)
        homeClass.CheckList.adapter = CustomAdapter(vm.data.value!!)

        vm.updateCheckList.observe(this){
            val adapter =  homeClass.CheckList.adapter as CustomAdapter
            adapter.setData(vm.data.value!!)
            homeClass.CheckList.adapter?.notifyDataSetChanged()
        }
    }
    private fun setMainParam(){
        homeClass.OverallStreak.text = vm.overallStreak.value
        homeClass.BestStreak.text = vm.bestStreak.value
        homeClass.Hour.text = vm.timeTitle.value
        homeClass.Hello.text = vm.userName.value
    }
    private fun addPieChart(){
        vm.notDoneHabits.observe(this){
            AddPieChartUseCase().execute(homeClass.PieChart, vm.doneHabits.value!!, vm.data.value!!.size)
        }
        vm.doneHabits.observe(MAIN){
            AddPieChartUseCase().execute(homeClass.PieChart, vm.doneHabits.value!!, vm.data.value!!.size)
        }
        vm.label.observe(this){
            homeClass.DescDone.text = it
        }
    }

    private fun createCalendar(){
        vm.dayInWeek.value?.let { setColorDate(it) }
        if (vm.currentMonth.value?.get(0)?.length == 4){
            homeClass.tvMonth1.textSize = 11F
            homeClass.tvMonth2.textSize = 11F
            homeClass.tvMonth3.textSize = 11F
            homeClass.tvMonth4.textSize = 11F
            homeClass.tvMonth5.textSize = 11F
            homeClass.tvMonth6.textSize = 11F
            homeClass.tvMonth7.textSize = 11F
        }

        homeClass.tvDay1.text = vm.weeklyDate.value?.get(0).toString()
        homeClass.tvDay2.text = vm.weeklyDate.value?.get(1).toString()
        homeClass.tvDay3.text = vm.weeklyDate.value?.get(2).toString()
        homeClass.tvDay4.text = vm.weeklyDate.value?.get(3).toString()
        homeClass.tvDay5.text = vm.weeklyDate.value?.get(4).toString()
        homeClass.tvDay6.text = vm.weeklyDate.value?.get(5).toString()
        homeClass.tvDay7.text = vm.weeklyDate.value?.get(6).toString()

        homeClass.tvMonth1.text = vm.currentMonth.value?.get(0)
        homeClass.tvMonth2.text = vm.currentMonth.value?.get(1)
        homeClass.tvMonth3.text = vm.currentMonth.value?.get(2)
        homeClass.tvMonth4.text = vm.currentMonth.value?.get(3)
        homeClass.tvMonth5.text = vm.currentMonth.value?.get(4)
        homeClass.tvMonth6.text = vm.currentMonth.value?.get(5)
        homeClass.tvMonth7.text = vm.currentMonth.value?.get(6)
    }

    private fun setColorDate(currentDate: Int) {
        val items = listOf(
            homeClass.CalendarItem1,
            homeClass.CalendarItem2,
            homeClass.CalendarItem3,
            homeClass.CalendarItem4,
            homeClass.CalendarItem5,
            homeClass.CalendarItem6,
            homeClass.CalendarItem7
        )
        items[currentDate - 1].setBackgroundResource(R.drawable.calendar_background_colored)

        val days = listOf(
            homeClass.tvDay1,
            homeClass.tvDay2,
            homeClass.tvDay3,
            homeClass.tvDay4,
            homeClass.tvDay5,
            homeClass.tvDay6,
            homeClass.tvDay7
        )
        val months = listOf(
            homeClass.tvMonth1,
            homeClass.tvMonth2,
            homeClass.tvMonth3,
            homeClass.tvMonth4,
            homeClass.tvMonth5,
            homeClass.tvMonth6,
            homeClass.tvMonth7
        )
        val color = Color.parseColor("#FFFFFF")
        days[currentDate - 1].setTextColor(color)
        months[currentDate - 1].setTextColor(color)
    }
}