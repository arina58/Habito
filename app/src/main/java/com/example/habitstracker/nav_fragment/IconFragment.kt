package com.example.habitstracker.nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentIconBinding


class IconFragment : Fragment() {

lateinit var IconClass: FragmentIconBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        IconClass = FragmentIconBinding.inflate(layoutInflater, container, false)
        return IconClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        MAIN.navController.navigate(R.id.action_iconFragment5_to_enterNameFragment2)
    }

}