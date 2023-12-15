package com.example.habitstracker.nav_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentSoundAndVebrationBinding

class SoundAndVebrationFragment : Fragment() {
    lateinit var SaVClass: FragmentSoundAndVebrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        SaVClass = FragmentSoundAndVebrationBinding.inflate(layoutInflater, container, false)
        var main_coor = MAIN.findViewById<CoordinatorLayout>(R.id.main_coord_lay)
        main_coor.visibility = View.GONE
        return SaVClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SaVClass.BackButton.setOnClickListener {
            MAIN.navController.navigate(R.id.action_soundAndVebrationFragment_to_settingsFragment)
        }
    }

}