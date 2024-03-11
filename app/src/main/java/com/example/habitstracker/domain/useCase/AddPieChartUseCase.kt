package com.example.habitstracker.domain.useCase

import android.graphics.Color
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class AddPieChartUseCase {
    fun execute(item: PieChart, value1: Int, value2: Int){
        item.clearChart()
        for (i in 1..value1) {
            item.addPieSlice(
                PieModel("Integer $i", (100/(value1 + value2)).toFloat(),
                    Color.parseColor("#8D4AF8")
                )
            )
        }
        for (i in 1..value2) {
            item.addPieSlice(
                PieModel("Integer ${i+value1}", (100/(value1 + value2)).toFloat(),
                    Color.parseColor("#CFB1FF")
                )
            )
        }
        item.startAnimation()
    }
}