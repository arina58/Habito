package com.example.habitstracker.presentation.finishFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentFinishHabitsListBinding

class FinishFragment : Fragment() {
    private val finishHabitsClass: FragmentFinishHabitsListBinding by lazy {
        FragmentFinishHabitsListBinding.inflate(layoutInflater)
    }
    private val vm: FinishViewModel by lazy {
        ViewModelProvider(this)[FinishViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return finishHabitsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FinishHabitsAdapter()

        finishHabitsClass.List.adapter = adapter

        adapter.clickListener = {
            vm.returnHabit(it)
        }

        finishHabitsClass.BackButton.setOnClickListener {
            findNavController().navigate(R.id.action_finishHabitsFragment_to_homeFragment)
        }

        vm.data.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }
}