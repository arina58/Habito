package com.example.habitstracker.nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitstracker.*
import com.example.habitstracker.databinding.FragmentAnalysisBinding
import com.example.habitstracker.domain.adapter.ProgressBarAdapter
import com.example.habitstracker.domain.model.ProgressViewModel
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase


class AnalysisFragment : Fragment() {
    private lateinit var analysisClass: FragmentAnalysisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        analysisClass = FragmentAnalysisBinding.inflate(layoutInflater, container, false)
        return analysisClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        analysisClass.ProgressList.isNestedScrollingEnabled = false
        addProgressList()
    }

    private fun addProgressList(){
        val recyclerview = analysisClass.ProgressList
        recyclerview.layoutManager = LinearLayoutManager(MAIN)
        val data = ArrayList<ProgressViewModel>()

        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0", "1")).forEach {
            data.add(ProgressViewModel(it.title, " from 7 days target", it.date_of_week))
        }

        val adapter = ProgressBarAdapter(data)
        recyclerview.adapter = adapter
    }

}