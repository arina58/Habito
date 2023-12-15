package com.example.habitstracker.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentAboutAppBinding
import com.example.habitstracker.databinding.FragmentAnalysisBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutAppFragment : Fragment() {
    lateinit var AboutAppClass: FragmentAboutAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AboutAppClass = FragmentAboutAppBinding.inflate(layoutInflater, container, false)

        return AboutAppClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var main_coor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        main_coor.visibility = View.GONE
        AboutAppClass.BackButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_aboutAppFragment_to_settingsFragment)
        }
    }

}