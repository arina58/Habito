package com.example.habitstracker.nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.databinding.FragmentAnalysisBinding
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
        vm = ViewModelProvider(this)[AnalysisViewModel::class.java]
        analysisClass.ProgressList.isNestedScrollingEnabled = false
        vm.addProgressList(analysisClass.ProgressList)
    }
}