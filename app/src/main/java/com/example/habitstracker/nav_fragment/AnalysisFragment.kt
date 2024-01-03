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


        data.add(ProgressViewModel("Meditation", "3 from 7 days target", 3))
        data.add(ProgressViewModel("Books",  "5 from 7 days target", 5))
        data.add(ProgressViewModel("Running",  "1 from 7 days target", 1))


        val adapter = ProgressBarAdapter(data)
        recyclerview.adapter = adapter
    }

}