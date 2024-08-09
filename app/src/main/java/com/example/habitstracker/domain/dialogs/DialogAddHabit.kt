package com.example.habitstracker.domain.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.DialogFragment

import com.example.habitstracker.databinding.NewGoalBinding
import com.example.habitstracker.di.DaggerAppComponent
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.AddHabitUseCase
import com.example.habitstracker.domain.useCase.ValidateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DialogAddHabit : DialogFragment() {
    private lateinit var addHabitClass: NewGoalBinding

    @Inject
    lateinit var addHabitUseCase: AddHabitUseCase

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addHabitClass = NewGoalBinding.inflate(layoutInflater, container, false)

        return addHabitClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = DaggerAppComponent.factory().create(requireActivity().application, requireActivity().applicationContext)
        component.inject(this)

        addHabitClass.NameGoalText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase(requireActivity()).validateName(
                    addHabitClass.NameGoalText,
                    addHabitClass.NameGoal
                )
            }

        })

        addHabitClass.DescriptionText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase(requireActivity()).validateDescription(
                    addHabitClass.DescriptionText,
                    addHabitClass.Description
                )
            }
        })

        addHabitClass.NumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase(requireActivity()).validateNumber(
                    addHabitClass.NumberText,
                    addHabitClass.Number
                )
            }
        })

        addHabitClass.ButtonCreate.setOnClickListener {

            if (ValidateUseCase(requireActivity()).validateName(
                    addHabitClass.NameGoalText,
                    addHabitClass.NameGoal
                ) &&
                ValidateUseCase(requireActivity()).validateNumber(
                    addHabitClass.NumberText,
                    addHabitClass.Number
                ) &&
                ValidateUseCase(requireActivity()).validateDescription(
                    addHabitClass.DescriptionText,
                    addHabitClass.Description
                )
            ) {
                scope.launch {
                    addHabitUseCase(
                        HabitItem(
                            0, addHabitClass.NameGoalText.text.toString(),
                            addHabitClass.NumberText.text.toString().toInt(), 0, 0, 0, 0,
                            addHabitClass.DescriptionText.text.toString()
                        )
                    )
                }

//                MAIN.vmHome.updateData(1, null)
//                MAIN.vmAnalysis?.addItem()
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