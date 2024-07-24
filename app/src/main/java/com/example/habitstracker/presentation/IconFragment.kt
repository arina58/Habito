package com.example.habitstracker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habitstracker.databinding.FragmentIconBinding


class IconFragment : Fragment() {

    private lateinit var iconClass: FragmentIconBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        iconClass = FragmentIconBinding.inflate(layoutInflater, container, false)
        return iconClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        MAIN.navController.navigate(R.id.action_iconFragment5_to_enterNameFragment2)
    }

}