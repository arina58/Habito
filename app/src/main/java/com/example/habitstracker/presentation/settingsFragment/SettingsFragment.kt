package com.example.habitstracker.presentation.settingsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.HabitoApp
import com.example.habitstracker.databinding.FragmentSettingsBinding
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class SettingsFragment : Fragment() {
    private val settingsClass by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm by lazy {
        ViewModelProvider(this, vmFactory)[SettingsViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as HabitoApp).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return settingsClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

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