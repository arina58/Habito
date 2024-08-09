package com.example.habitstracker.presentation.homeFragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.*
import com.example.habitstracker.databinding.FragmentHomeBinding
import com.example.habitstracker.di.DaggerAppComponent
import com.example.habitstracker.domain.dialogs.DialogAddHabit
import com.example.habitstracker.domain.dialogs.DialogChangeHabit
import com.example.habitstracker.domain.useCase.AddPieChartUseCase
import com.example.habitstracker.presentation.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class HomeFragment : Fragment() {
    private val homeClass: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: HomeViewModel by lazy {
        ViewModelProvider(this, vmFactory)[HomeViewModel::class.java]
    }

    @Inject
    lateinit var addPieChartUseCase: AddPieChartUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return homeClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = DaggerAppComponent.factory().create(
            requireActivity().application,
            requireActivity().applicationContext
        )

        component.inject(this)

        val bar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bar.visibility = View.VISIBLE

        homeClass.CheckList.isNestedScrollingEnabled = false

        homeClass.ChartText3.text =
            requireActivity().resources.getStringArray(R.array.chart_text)[2]

        setMainParam()
        createCalendar()
        addPieChart()
        addCheckList()

        homeClass.ActionButton.setOnClickListener {
            DialogAddHabit().show(requireActivity().supportFragmentManager, "dialogAdd")
        }
        homeClass.FinishButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_finishHabitsFragment)
        }

    }

    private fun addCheckList() {
        val adapter = CustomAdapter()
        homeClass.CheckList.adapter = adapter

        vm.notCompletedHabits.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        adapter.checkBoxListener = {
            vm.markItemCompleted(it)
        }

        adapter.changeItemListener = {
            val dialog = DialogChangeHabit.newInstance(it)
            dialog.show(requireActivity().supportFragmentManager, "dialogChange")
        }

        adapter.deleteItemListener = {
            vm.deleteItem(it)
        }
    }

    private fun setMainParam() {
        homeClass.OverallStreak.text = vm.overallStreak
        homeClass.BestStreak.text = vm.bestStreak
        homeClass.Hour.text = vm.timeTitle.value
        homeClass.Hello.text = vm.userName
    }

    private fun addPieChart() {
        vm.completedHabitsCount.observe(viewLifecycleOwner) { completedCount ->
            vm.notCompletedHabitsCount.observe(viewLifecycleOwner) { notCompletedCount ->
                addPieChartUseCase(
                    homeClass.PieChart,
                    completedCount,
                    notCompletedCount
                )
                Log.i("addPieChartUseCase", "$completedCount - $notCompletedCount")
                vm.updateLabel()
            }
        }

        vm.label.observe(viewLifecycleOwner) {
            homeClass.DescDone.text = it
        }
    }

    private fun createCalendar() {
        setColorDate(vm.dayInWeek)
        if (vm.currentMonth[0].length == 4) {
            homeClass.tvMonth1.textSize = 11F
            homeClass.tvMonth2.textSize = 11F
            homeClass.tvMonth3.textSize = 11F
            homeClass.tvMonth4.textSize = 11F
            homeClass.tvMonth5.textSize = 11F
            homeClass.tvMonth6.textSize = 11F
            homeClass.tvMonth7.textSize = 11F
        }

        homeClass.tvDay1.text = vm.weeklyDate[0].toString()
        homeClass.tvDay2.text = vm.weeklyDate[1].toString()
        homeClass.tvDay3.text = vm.weeklyDate[2].toString()
        homeClass.tvDay4.text = vm.weeklyDate[3].toString()
        homeClass.tvDay5.text = vm.weeklyDate[4].toString()
        homeClass.tvDay6.text = vm.weeklyDate[5].toString()
        homeClass.tvDay7.text = vm.weeklyDate[6].toString()

        homeClass.tvMonth1.text = vm.currentMonth[0]
        homeClass.tvMonth2.text = vm.currentMonth[1]
        homeClass.tvMonth3.text = vm.currentMonth[2]
        homeClass.tvMonth4.text = vm.currentMonth[3]
        homeClass.tvMonth5.text = vm.currentMonth[4]
        homeClass.tvMonth6.text = vm.currentMonth[5]
        homeClass.tvMonth7.text = vm.currentMonth[6]
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