package com.example.habitstracker.presentation.finishHabit

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.Gravity.CENTER
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.HabitoApp
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FinishGoalBinding
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class FinishHabit : DialogFragment() {
    private lateinit var binding: FinishGoalBinding

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: FinishHabitViewModel by lazy {
        ViewModelProvider(this, vmFactory)[FinishHabitViewModel::class.java]
    }

    companion object {
        fun newInstance(value: Int): FinishHabit {
            val args = Bundle()
            args.putInt("id", value)
            val fragment = FinishHabit()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog?.window?.attributes?.width = (resources.displayMetrics.widthPixels - 40)

        dialog?.window?.setGravity(CENTER)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FinishGoalBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = (requireActivity().application as HabitoApp).component
        component.inject(this)

        val id = requireArguments().getInt("id", 0)

        vm.getHabit(id)

        vm.habit.observe(viewLifecycleOwner) {
            binding.NameGoalText.text =
                Editable.Factory.getInstance().newEditable(it.title)
            binding.NumberText.text =
                Editable.Factory.getInstance().newEditable(it.period.toString())
        }

        setTextChangedListener()

        vm.shouldCloseDialog.observe(viewLifecycleOwner) {
            dismiss()
        }

        binding.ButtonFinish.setOnClickListener {
            vm.deleteHabit(id)
        }

        binding.ButtonContinue.setOnClickListener {
            binding.ButtonFinish.visibility = GONE
            binding.ButtonContinue.visibility = GONE
            binding.Number.visibility = VISIBLE
            binding.NameGoal.isEnabled = true
            binding.NameGoal.helperText = resources.getString(R.string.helper_text)
            binding.ButtonProlong.visibility = VISIBLE
        }

        binding.ButtonProlong.setOnClickListener {
            vm.updateHabit(binding.NameGoalText, binding.NumberText)
        }

        vm.errorName.observe(viewLifecycleOwner){
            binding.NameGoal.error = if(it){
                resources.getString(R.string.error_validate_name_goal)
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

        binding.NumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                vm.resetErrorCount()
            }
        })
    }
}