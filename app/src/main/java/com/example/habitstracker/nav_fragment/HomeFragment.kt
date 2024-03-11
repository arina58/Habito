package com.example.habitstracker.nav_fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitstracker.domain.adapter.CustomAdapter
import com.example.habitstracker.domain.model.ItemsViewModel
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.STATUS
import com.example.habitstracker.databinding.FragmentHomeBinding
import com.example.habitstracker.domain.model.HabitViewModel
import com.example.habitstracker.domain.useCase.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {
    private lateinit var homeClass: FragmentHomeBinding
    lateinit var adapter: CustomAdapter
    private val getWeeklyDate = GetWeeklyDateUseCase()
    private val getCurrentMonth = GetCurrentMonthUseCase()
    private val getUserName = GetUserNameUseCase()
    private val addHabit = AddHabitUseCase()
    private val getHabits = GetHabitsFromDBUseCase()
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
        setStreak()
        createCalendar()
        homeClass.CheckList.isNestedScrollingEnabled = false
        addPieChart()
        addCheckList()
        homeClass.ActionButton.setOnClickListener {
            showAddDialog()
        }
        homeClass.FinishButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_homeFragment_to_finishHabitsFragment)
        }
    }
    private fun setStreak(){
        val streaks = GetStreakUseCase().execute()
        homeClass.OverallStreak.text = "Overall streak: ${streaks[0]} days"
        homeClass.BestStreak.text = "Overall best streak: ${streaks[1]} days"
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
            val title = dialog.findViewById<EditText>(R.id.NameGoal).text.toString()
            val period = dialog.findViewById<EditText>(R.id.editTextNumber).text.toString().toInt()
            if(title.length in 1..255 && period in 2..365) {
                addHabit.execute(HabitViewModel(0, title, period, 0, 0, 0, 0))
                dialog.cancel()

                addCheckList()
                addPieChart()
            }

        }
    }

    private fun addPieChart(){
        val done = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1"))
        val undone = GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0"))
        AddPieChartUseCase().execute(homeClass.PieChart, done.size, undone.size)

        homeClass.DescDone.text = "${done.size} of ${done.size + undone.size} habits"
    }

    private fun addCheckList(){
        val recyclerview = homeClass.CheckList
        recyclerview.layoutManager = LinearLayoutManager(MAIN)

        val data = ArrayList<ItemsViewModel>()
        getHabits.execute(STATUS, arrayOf("0")).forEach {
            data.add(ItemsViewModel(it.id, it.title, "Current: ${it.current}", "Best: ${it.best}"))
        }

        adapter = CustomAdapter(data)
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

        homeClass.tvMonth1.text = currentMonth[0]
        homeClass.tvMonth2.text = currentMonth[1]
        homeClass.tvMonth3.text = currentMonth[2]
        homeClass.tvMonth4.text = currentMonth[3]
        homeClass.tvMonth5.text = currentMonth[4]
        homeClass.tvMonth6.text = currentMonth[5]
        homeClass.tvMonth7.text = currentMonth[6]
    }

    private fun setColorDate(currentDate: Int) {
        val textColor = Color.parseColor("#8D4AF8")
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

        days[currentDate - 1].setTextColor(textColor)
        months[currentDate - 1].setTextColor(textColor)
    }
}