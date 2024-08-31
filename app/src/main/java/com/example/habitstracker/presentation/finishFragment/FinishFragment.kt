package com.example.habitstracker.presentation.finishFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.HabitoApp
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentFinishHabitsListBinding
import com.example.habitstracker.presentation.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class FinishFragment : Fragment() {
    private val finishHabitsClass: FragmentFinishHabitsListBinding by lazy {
        FragmentFinishHabitsListBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: FinishViewModel by lazy {
        ViewModelProvider(this, vmFactory)[FinishViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as HabitoApp).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return finishHabitsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        val bar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bar.visibility = View.GONE

        val adapter = FinishHabitsAdapter()

        finishHabitsClass.List.adapter = adapter

        adapter.clickListener = {
            vm.returnHabit(it)
        }

        finishHabitsClass.BackButton.setOnClickListener {
            findNavController().navigate(R.id.action_finishHabitsFragment_to_homeFragment)
            bar.visibility = View.VISIBLE
        }

        vm.data.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }
}