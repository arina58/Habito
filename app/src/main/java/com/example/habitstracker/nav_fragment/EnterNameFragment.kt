package com.example.habitstracker.nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.databinding.FragmentEnterNameBinding
import com.example.habitstracker.viewModel.UserNameViewModel

class EnterNameFragment : Fragment() {

    private lateinit var enterNameClass: FragmentEnterNameBinding
    private lateinit var vm: UserNameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        enterNameClass = FragmentEnterNameBinding.inflate(layoutInflater, container, false)
        return enterNameClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this)[UserNameViewModel::class.java]
        enterNameClass.nameEnterCont.setOnClickListener {
            vm.saveUserName(enterNameClass.EditUsername.text.toString())
        }
    }
}