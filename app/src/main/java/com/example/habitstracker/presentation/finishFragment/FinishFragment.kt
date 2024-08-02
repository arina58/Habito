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
import com.example.habitstracker.di.DaggerAppComponent
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class FinishFragment : Fragment() {

    private val component = DaggerAppComponent.factory().create(
        requireActivity().application,
        requireActivity().applicationContext)

    private val finishHabitsClass: FragmentFinishHabitsListBinding by lazy {
        FragmentFinishHabitsListBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: FinishViewModel by lazy {
        ViewModelProvider(this, vmFactory)[FinishViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return finishHabitsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)
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