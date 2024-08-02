package com.example.habitstracker.domain.useCase

import android.graphics.Color
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class AddPieChartUseCase {
    operator fun invoke(item: PieChart, value1: Int, value2: Int){
        item.clearChart()
        var v2 = value2
        if(value1 + value2 == 0) v2 = 1

        for (i in 1..value1) {
            item.addPieSlice(
                PieModel("Integer $i", (100/(value1 + v2)).toFloat(),
                    Color.parseColor("#C54AF7")
                )
            )
        }
        for (i in 1..v2) {
            item.addPieSlice(
                PieModel("Integer ${i+value1}", (100/(value1 + v2)).toFloat(),
                    Color.parseColor("#DFD7E5")
                )
            )
        }
        item.startAnimation()
    }
}