package com.example.habitstracker.presentation.addHabitDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.R
import com.example.habitstracker.databinding.NewGoalBinding
import com.example.habitstracker.di.DaggerAppComponent
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class AddHabit : DialogFragment() {
    private lateinit var binding: NewGoalBinding

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: AddHabitViewModel by lazy {
        ViewModelProvider(this, vmFactory)[AddHabitViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewGoalBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = DaggerAppComponent.factory()
            .create(requireActivity().application, requireActivity().applicationContext)
        component.inject(this)

        setTextChangedListener()

        binding.ButtonCreate.setOnClickListener {
            vm.addHabit(binding.NameGoalText, binding.DescriptionText, binding.NumberText)
        }

        vm.shouldCloseDialog.observe(viewLifecycleOwner){
            dismiss()
        }

        vm.errorName.observe(viewLifecycleOwner){
            binding.NameGoal.error = if(it){
                resources.getString(R.string.error_validate_name_goal)
            }else{
                null
            }
        }

        vm.errorDescription.observe(viewLifecycleOwner){
            binding.Description.error = if(it){
                resources.getString(R.string.error_validate_description)
            }else{
                null
            }
        }

        vm.errorCount.observe(viewLifecycleOwner){
            binding.Number.error = if(it){
                resources.getString(R.string.error_validate_number)
            }else{
                null
            }
        }
    }

    private fun setTextChangedListener() {
        binding.NameGoalText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                vm.resetErrorName()
            }

        })

        binding.DescriptionText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                vm.resetErrorDescription()
            }
        })

        binding.NumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                vm.resetErrorCount()
            }
        })
    }

    override fun onStart() {
        super.onStart()


        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog?.window?.attributes?.width = (resources.displayMetrics.widthPixels - 40)

        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}