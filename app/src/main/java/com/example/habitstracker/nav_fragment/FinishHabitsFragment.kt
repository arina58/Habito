package com.example.habitstracker.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.STATUS
import com.example.habitstracker.databinding.FragmentFinishHabitsListBinding
import com.example.habitstracker.domain.adapter.FinishHabitsAdapter
import com.example.habitstracker.domain.model.HabitFinishItemModel
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase

class FinishHabitsFragment : Fragment() {
    private lateinit var finishHabitsClass: FragmentFinishHabitsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        finishHabitsClass = FragmentFinishHabitsListBinding.inflate(layoutInflater, container, false)
        return finishHabitsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mainCoordinator = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        mainCoordinator.visibility = View.GONE

        val recyclerview = finishHabitsClass.List
        recyclerview.layoutManager = LinearLayoutManager(MAIN)

        val data = ArrayList<HabitFinishItemModel>()
        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("1")).forEach {
            data.add(HabitFinishItemModel(it.id, it.title))
        }

        val adapter = FinishHabitsAdapter(data)
        recyclerview.adapter = adapter

        finishHabitsClass.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.back -> {
                    MAIN.navController.navigate(R.id.action_finishHabitsFragment_to_homeFragment)
                    true
                }
                else -> false
            }
        }
    }
}