package com.example.habitstracker.nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentEnterNameBinding
import com.example.habitstracker.domain.useCase.SaveUserNameUseCase


class EnterNameFragment : Fragment() {

    private lateinit var enterNameClass: FragmentEnterNameBinding
    private val saveUserName = SaveUserNameUseCase()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        enterNameClass = FragmentEnterNameBinding.inflate(layoutInflater, container, false)
        return enterNameClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enterNameClass.nameEnterCont.setOnClickListener {

            saveUserName.execute(enterNameClass.EditUsername.text.toString())
            MAIN.navController.navigate(R.id.action_enterNameFragment_to_homeFragment)
        }
    }
}