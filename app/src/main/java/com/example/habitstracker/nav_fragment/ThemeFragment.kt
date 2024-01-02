package com.example.habitstracker.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentThemeBinding
import com.example.habitstracker.domain.useCase.SaveNameThemeUseCase

class ThemeFragment : Fragment() {
    lateinit var ThemeClass: FragmentThemeBinding
    private val saveNameTheme = SaveNameThemeUseCase()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ThemeClass = FragmentThemeBinding.inflate(layoutInflater, container, false)
        var main_coor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        main_coor.visibility = View.GONE
        return ThemeClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ThemeClass.BackButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_themeFragment_to_settingsFragment)
        }

        ThemeClass.LightTheme.setOnClickListener{
            saveNameTheme.execute("LIGHT_THEME")
        }

        ThemeClass.DarkTheme.setOnClickListener {
            saveNameTheme.execute("DARK_THEME")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

}