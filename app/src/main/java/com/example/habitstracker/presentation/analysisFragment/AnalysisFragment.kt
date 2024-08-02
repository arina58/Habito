package com.example.habitstracker.presentation.analysisFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.databinding.FragmentAnalysisBinding

class AnalysisFragment : Fragment() {
    private val analysisClass: FragmentAnalysisBinding by lazy {
        FragmentAnalysisBinding.inflate(layoutInflater)
    }
    private val vm: AnalysisViewModel by lazy {
        ViewModelProvider(this)[AnalysisViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return analysisClass.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProgressBarAdapter()

        analysisClass.ProgressList.adapter = adapter

        adapter.clickListener = {
            vm.updateLabel(it)
        }

        vm.data.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        vm.title.observe(viewLifecycleOwner){
            analysisClass.Title.text = vm.title.value
        }

        vm.description.observe(viewLifecycleOwner){
            analysisClass.Description.text = vm.description.value
        }

        vm.days.observe(viewLifecycleOwner){
            analysisClass.DaysLeft.text = vm.days.value
        }
    }
}