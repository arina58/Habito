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

class AboutAppFragment : Fragment() {
    private lateinit var aboutAppClass: FragmentAboutAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        aboutAppClass = FragmentAboutAppBinding.inflate(layoutInflater, container, false)

        return aboutAppClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainCoor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        mainCoor.visibility = View.GONE
        aboutAppClass.BackButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_aboutAppFragment_to_settingsFragment)
        }
    }

}