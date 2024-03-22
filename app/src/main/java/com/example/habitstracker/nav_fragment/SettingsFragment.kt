package com.example.habitstracker.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentSettingsBinding
import com.example.habitstracker.viewModel.SettingsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment() {
    private lateinit var settingsClass: FragmentSettingsBinding
    private lateinit var vm: SettingsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsClass = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return settingsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainCoordination = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        val bar = MAIN.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        mainCoordination.visibility = View.VISIBLE
        bar.background = null

        vm = ViewModelProvider(this)[SettingsViewModel::class.java]

        settingsClass.SwitchTheme.isChecked = vm.stateSwitchTheme.value == true
        settingsClass.NotificationSwitch.isChecked = vm.stateNotification.value!!

        settingsClass.NotificationSwitch.setOnClickListener{
            vm.changeNotification(settingsClass.NotificationSwitch.isChecked)
        }

        settingsClass.SwitchTheme.setOnClickListener{
            vm.changeTheme(settingsClass.SwitchTheme.isChecked)
        }
    }
}