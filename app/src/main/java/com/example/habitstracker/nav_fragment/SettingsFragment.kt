package com.example.habitstracker.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.habitstracker.DARK_THEME
import com.example.habitstracker.LIGHT_THEME
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentSettingsBinding
import com.example.habitstracker.domain.useCase.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsFragment : Fragment() {
    private lateinit var settingsClass: FragmentSettingsBinding
    private val saveNameTheme = SaveNameThemeUseCase()
    private val switchTheme = SwitchThemeUseCase()
    private val getNameTheme = GetNameThemeUseCase()
    private val saveSoundAndVibration = SaveSoundAndVibrationUseCase()
    private val getSoundAndVibration = GetSoundAndVibrationUseCase()
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

        setSwitch()

        settingsClass.SwitchTheme.setOnClickListener{
            if(settingsClass.SwitchTheme.isChecked){
                saveNameTheme.execute(DARK_THEME)
                switchTheme.execute(DARK_THEME)
            }else{
                saveNameTheme.execute(LIGHT_THEME)
                switchTheme.execute(LIGHT_THEME)
            }
        }
    }

    fun setSwitch(){
        if(getNameTheme.execute() == DARK_THEME)
            settingsClass.SwitchTheme.isChecked = true
    }
}