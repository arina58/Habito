package com.example.habitstracker.presentation.registerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.HabitoApp
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentUserNameBinding
import com.example.habitstracker.presentation.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class UserNameFragment : Fragment() {
    private val binding: FragmentUserNameBinding by lazy {
        FragmentUserNameBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: UserNameViewModel by lazy {
        ViewModelProvider(this, vmFactory)[UserNameViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as HabitoApp).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        val bar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bar.visibility = View.GONE

        binding.nameEnterCont.setOnClickListener {
            vm.saveUserName(binding.NameText.text)
        }

        vm.changeFragment.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_userNameFragment_to_homeFragment)
        }
    }
}