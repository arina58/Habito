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
import com.example.habitstracker.databinding.FragmentThemeBinding
import com.example.habitstracker.domain.useCase.GetNameThemeUseCase
import com.example.habitstracker.domain.useCase.SaveNameThemeUseCase
import com.example.habitstracker.domain.useCase.SwitchThemeUseCase

class ThemeFragment : Fragment() {
    private lateinit var themeClass: FragmentThemeBinding
    private val saveNameTheme = SaveNameThemeUseCase()
    private val switchTheme = SwitchThemeUseCase()
    private val getNameTheme = GetNameThemeUseCase()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        themeClass = FragmentThemeBinding.inflate(layoutInflater, container, false)
        val main_coor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        main_coor.visibility = View.GONE
        return themeClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themeClass.BackButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_themeFragment_to_settingsFragment)
        }

        themeClass.LightTheme.setOnClickListener{
            saveNameTheme.execute(LIGHT_THEME)
            switchTheme.execute(LIGHT_THEME)
        }

        themeClass.DarkTheme.setOnClickListener {
            saveNameTheme.execute(DARK_THEME)
            switchTheme.execute(DARK_THEME)
        }
    }

}