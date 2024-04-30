package com.example.habitstracker.nav_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentFinishHabitsListBinding
import com.example.habitstracker.domain.adapter.FinishHabitsAdapter
import com.example.habitstracker.domain.model.HabitFinishItemData
import com.example.habitstracker.viewModel.FinishViewModel

class FinishFragment : Fragment() {
    private lateinit var finishHabitsClass: FragmentFinishHabitsListBinding
    private lateinit var vm: FinishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        finishHabitsClass = FragmentFinishHabitsListBinding.inflate(layoutInflater, container, false)
        return finishHabitsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainCoordinator = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        mainCoordinator.visibility = View.GONE

        MAIN.vmFinish = ViewModelProvider(this)[FinishViewModel::class.java]
        vm = MAIN.vmFinish!!

        finishHabitsClass.List.layoutManager = LinearLayoutManager(MAIN)
        finishHabitsClass.List.adapter = vm.data.value?.let { FinishHabitsAdapter(it) }

        finishHabitsClass.BackButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_finishHabitsFragment_to_homeFragment)
        }

        vm.update.observe(this){
            var data: ArrayList<HabitFinishItemData> = arrayListOf()
            val adapter = finishHabitsClass.List.adapter as FinishHabitsAdapter
            vm.data.value?.let { data = it }
            adapter.setData(data)
            Log.d("MY", "update")
            finishHabitsClass.List.adapter?.notifyDataSetChanged()
        }
    }
}