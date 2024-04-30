package com.example.habitstracker.nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitstracker.MAIN
import com.example.habitstracker.databinding.FragmentAnalysisBinding
import com.example.habitstracker.domain.adapter.ProgressBarAdapter
import com.example.habitstracker.viewModel.AnalysisViewModel

class AnalysisFragment : Fragment() {
    private lateinit var analysisClass: FragmentAnalysisBinding
    private lateinit var vm: AnalysisViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        analysisClass = FragmentAnalysisBinding.inflate(layoutInflater, container, false)
        return analysisClass.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MAIN.vmAnalysis = ViewModelProvider(this)[AnalysisViewModel::class.java]
        vm = MAIN.vmAnalysis!!

        analysisClass.ProgressList.layoutManager = LinearLayoutManager(MAIN)
        val adapter = vm.data.value?.let { ProgressBarAdapter(it) }

        adapter?.setOnItemClickListener { progressData ->
            vm.updateLabel(progressData)
        }

        analysisClass.ProgressList.adapter = adapter

        vm.data.observe(this){
            val adapter = analysisClass.ProgressList.adapter as ProgressBarAdapter
            adapter.setData(vm.data.value!!)
            analysisClass.ProgressList.adapter?.notifyDataSetChanged()
        }

        vm.title.observe(this){
            analysisClass.Title.text = vm.title.value
        }

        vm.description.observe(this){
            analysisClass.Description.text = vm.description.value
        }

        vm.days.observe(this){
            analysisClass.DaysLeft.text = vm.days.value
        }
    }
}