package com.example.habitstracker.domain.dialogs

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.habitstracker.MAIN
import com.example.habitstracker.databinding.NewGoalBinding
import com.example.habitstracker.domain.model.HabitViewModel
import com.example.habitstracker.domain.useCase.AddHabitUseCase

class DialogAddHabit: DialogFragment() {
    private lateinit var addHabitClass: NewGoalBinding

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addHabitClass = NewGoalBinding.inflate(layoutInflater, container, false)

        return addHabitClass.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.setCancelable(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addHabitClass.ButtonCreate.setOnClickListener {

            if (addHabitClass.NameGoal.text.length in 1..255 && addHabitClass.editTextNumber.text.toString() != "" && addHabitClass.editTextNumber.text.toString().length < 4 && addHabitClass.editTextNumber.text.toString().toInt() in 2..365) {
                AddHabitUseCase().execute(HabitViewModel(0, addHabitClass.NameGoal.text.toString(), addHabitClass.editTextNumber.text.toString().toInt(), 0, 0, 0, 0))

                MAIN.vmHome.updateData(1, null)

                dismiss()
            }
        }
    }
}