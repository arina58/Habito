package com.example.habitstracker.presentation.settingsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.databinding.FragmentSettingsBinding

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

        vm = ViewModelProvider(this)[SettingsViewModel::class.java]

        settingsClass.SwitchTheme.isChecked = vm.stateSwitchTheme.value == true
        settingsClass.NotificationSwitch.isChecked = vm.stateNotification.value!!

        settingsClass.NotificationSwitch.setOnClickListener{
            vm.changeNotification(requireActivity(), settingsClass.NotificationSwitch.isChecked)
        }

        settingsClass.SwitchTheme.setOnClickListener{
            vm.changeTheme(requireActivity(), settingsClass.SwitchTheme.isChecked)
        }
    }
}