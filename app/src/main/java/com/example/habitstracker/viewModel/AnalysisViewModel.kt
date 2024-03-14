package com.example.habitstracker.viewModel

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitstracker.MAIN
import com.example.habitstracker.STATUS
import com.example.habitstracker.domain.adapter.ProgressBarAdapter
import com.example.habitstracker.domain.model.ProgressViewModel
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase

class AnalysisViewModel: ViewModel() {

    fun addProgressList(list: RecyclerView){
        list.layoutManager = LinearLayoutManager(MAIN)
        val data = ArrayList<ProgressViewModel>()

        GetHabitsFromDBUseCase().execute(STATUS, arrayOf("0", "1")).forEach {
            data.add(ProgressViewModel(it.title, " from 7 days target", it.date_of_week))
        }

        val adapter = ProgressBarAdapter(data)
        list.adapter = adapter
    }
}