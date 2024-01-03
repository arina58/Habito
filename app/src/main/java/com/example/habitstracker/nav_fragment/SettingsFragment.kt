package com.example.habitstracker.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentSettingsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment() {
    private lateinit var settingsClass: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsClass = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return settingsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainCoor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        val bar = MAIN.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        mainCoor.visibility = View.VISIBLE
        bar.background = null

        settingsClass.SaV.setOnClickListener {
            MAIN.navController.navigate(R.id.action_settingsFragment_to_soundAndVebrationFragment)

        }
        settingsClass.Theme.setOnClickListener {
            MAIN.navController.navigate(R.id.action_settingsFragment_to_themeFragment)
        }
        settingsClass.AboutApp.setOnClickListener {
            MAIN.navController.navigate(R.id.action_settingsFragment_to_aboutAppFragment)
        }
    }
}