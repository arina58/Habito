package com.example.habitstracker.nav_fragment

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentEnterNameBinding


class EnterNameFragment : Fragment() {

    lateinit var EnterNameClass: FragmentEnterNameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        EnterNameClass = FragmentEnterNameBinding.inflate(layoutInflater, container, false)
        return EnterNameClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var pref = activity?.getSharedPreferences("User", MODE_PRIVATE)

        EnterNameClass.nameEnterCont.setOnClickListener {
            var editor = pref?.edit()
            val Username = EnterNameClass.EditUsername.text
            editor?.putString("username", Username.toString())
            editor?.commit()
            MAIN.navController.navigate(R.id.action_enterNameFragment_to_homeFragment)
        }
    }
}