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
import com.google.android.material.slider.Slider

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
        val mainCoor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        val bar = MAIN.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        mainCoor.visibility = View.VISIBLE
        bar.background = null

        vm = ViewModelProvider(this)[SettingsViewModel::class.java]

        settingsClass.SwitchTheme.isChecked = vm.stateSwitchTheme.value == true
        settingsClass.SwitchVibration.isChecked = vm.stateSwitchVibration.value!!
        settingsClass.soundSlider.value = vm.valueSoundSlider.value!!

        settingsClass.SwitchTheme.setOnClickListener{
            vm.changeTheme(settingsClass.SwitchTheme.isChecked)
        }

        settingsClass.SwitchVibration.setOnClickListener {
            vm.changeVibration(settingsClass.SwitchVibration.isChecked)
        }

        settingsClass.soundSlider.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            vm.saveSound(value)})
    }
}