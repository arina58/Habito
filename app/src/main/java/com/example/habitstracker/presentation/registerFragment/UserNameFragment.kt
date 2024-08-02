package com.example.habitstracker.presentation.registerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FragmentEnterNameBinding
import com.example.habitstracker.di.DaggerAppComponent
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class UserNameFragment : Fragment() {

    private val component = DaggerAppComponent.factory().create(
        requireActivity().application,
        requireActivity().applicationContext)

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
        component.inject(this)
        super.onViewCreated(view, savedInstanceState)

        enterNameClass.nameEnterCont.setOnClickListener {
            vm.saveUserName(requireActivity(), enterNameClass.NameText.text)
        }

        vm.changeFragment.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_enterNameFragment_to_homeFragment)
        }
    }
}