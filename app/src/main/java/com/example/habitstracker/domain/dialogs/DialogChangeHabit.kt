package com.example.habitstracker.domain.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.habitstracker.databinding.ChangeGoalBinding
import com.example.habitstracker.di.DaggerAppComponent
import com.example.habitstracker.domain.useCase.ValidateUseCase
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class DialogChangeHabit : DialogFragment() {
    private lateinit var changeHabitClass: ChangeGoalBinding

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: DialogChangeHabitViewModel by lazy {
        ViewModelProvider(this, vmFactory)[DialogChangeHabitViewModel::class.java]
    }

    companion object {
        fun newInstance(value: Int): DialogChangeHabit {
            val args = Bundle()
            args.putInt("id", value)
            val fragment = DialogChangeHabit()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        changeHabitClass = ChangeGoalBinding.inflate(layoutInflater, container, false)

        return changeHabitClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = DaggerAppComponent.factory()
            .create(requireActivity().application, requireActivity().applicationContext)
        component.inject(this)

        val id = requireArguments().getInt("id", 0)

        vm.getHabit(id)

        vm.habit.observe(viewLifecycleOwner) {
            changeHabitClass.NameGoalText.text =
                Editable.Factory.getInstance().newEditable(it.title)
            changeHabitClass.NumberText.text =
                Editable.Factory.getInstance().newEditable(it.period.toString())
            changeHabitClass.DescriptionText.text =
                Editable.Factory.getInstance().newEditable(it.description)
        }

        changeHabitClass.NameGoalText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase(requireActivity()).validateName(
                    changeHabitClass.NameGoalText,
                    changeHabitClass.NameGoal
                )
            }

        })

        changeHabitClass.DescriptionText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase(requireActivity()).validateDescription(
                    changeHabitClass.DescriptionText,
                    changeHabitClass.Description
                )
            }
        })

        changeHabitClass.NumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase(requireActivity()).validateNumber(
                    changeHabitClass.NumberText,
                    changeHabitClass.Number
                )
            }
        })

        changeHabitClass.ButtonCreate.setOnClickListener {
            if (ValidateUseCase(requireActivity()).validateName(
                    changeHabitClass.NameGoalText,
                    changeHabitClass.NameGoal
                ) &&
                ValidateUseCase(requireActivity()).validateNumber(
                    changeHabitClass.NumberText,
                    changeHabitClass.Number
                ) &&
                ValidateUseCase(requireActivity()).validateDescription(
                    changeHabitClass.DescriptionText,
                    changeHabitClass.Description
                )
            ) {

                vm.updateHabit(
                    changeHabitClass.NumberText.text,
                    changeHabitClass.NameGoalText.text,
                    changeHabitClass.DescriptionText.text
                )

//                MAIN.vmHome.updateData(0, item[0])

//                if(MAIN.vmAnalysis != null){
//                    MAIN.vmAnalysis?.updateItem(item[0].id)
//                }
                dismiss()
            }
        }

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

