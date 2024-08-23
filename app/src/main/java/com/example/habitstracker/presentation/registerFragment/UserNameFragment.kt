package com.example.habitstracker.presentation.registerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.HabitoApp
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentEnterNameBinding
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class UserNameFragment : Fragment() {
    private val enterNameClass: FragmentEnterNameBinding by lazy {
        FragmentEnterNameBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: UserNameViewModel by lazy {
        ViewModelProvider(this, vmFactory)[UserNameViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return enterNameClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = (requireActivity().application as HabitoApp).component
        component.inject(this)

        enterNameClass.nameEnterCont.setOnClickListener {
            vm.saveUserName(enterNameClass.NameText.text)
        }

        vm.changeFragment.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_enterNameFragment_to_homeFragment)
        }
    }
}