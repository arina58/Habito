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
  lateinit var SettingsClass: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        SettingsClass = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return SettingsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var main_coor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        var bar = MAIN.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        main_coor.visibility = View.VISIBLE
        bar.background = null
        SettingsClass.SaV.setOnClickListener {
            MAIN.navController.navigate(R.id.action_settingsFragment_to_soundAndVebrationFragment)

        }
        SettingsClass.Theme.setOnClickListener {
            MAIN.navController.navigate(R.id.action_settingsFragment_to_themeFragment)
        }
        SettingsClass.AboutApp.setOnClickListener {
            MAIN.navController.navigate(R.id.action_settingsFragment_to_aboutAppFragment)
        }
    }
}