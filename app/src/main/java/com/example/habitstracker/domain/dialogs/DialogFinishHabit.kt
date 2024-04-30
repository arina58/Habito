package com.example.habitstracker.domain.dialogs

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
import com.example.habitstracker.ID
import com.example.habitstracker.MAIN
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FinishGoalBinding
import com.example.habitstracker.domain.useCase.DeleteHabitUseCase
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import com.example.habitstracker.domain.useCase.ValidateUseCase

class DialogFinishHabit: DialogFragment() {
    private lateinit var finishHabitClass : FinishGoalBinding

    companion object {
        fun newInstance(value: Int): DialogFinishHabit {
            val args = Bundle()
            args.putInt("id", value)
            val fragment = DialogFinishHabit()
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
        finishHabitClass = FinishGoalBinding.inflate(layoutInflater, container, false)

        return finishHabitClass.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments!!.getInt("id", 0)
        val habit = GetHabitsFromDBUseCase().execute(ID, arrayOf("$id"), MAIN)

        finishHabitClass.NameGoalText.text = Editable.Factory.getInstance().newEditable(habit[0].title)
        finishHabitClass.NumberText.text = Editable.Factory.getInstance().newEditable(habit[0].period.toString())

        finishHabitClass.ButtonFinish.setOnClickListener {
            MAIN.vmHome.updateData(-1,
                GetHabitsFromDBUseCase().execute(ID, arrayOf("$id"), MAIN)[0])
            MAIN.vmHome.updateChart(-1)
            DeleteHabitUseCase().execute(id)

            dismiss()
        }

        finishHabitClass.ButtonContinue.setOnClickListener {
            finishHabitClass.ButtonFinish.visibility = GONE
            finishHabitClass.ButtonContinue.visibility = GONE
            finishHabitClass.Number.visibility = VISIBLE
            finishHabitClass.NameGoal.isEnabled = true
            finishHabitClass.NameGoal.helperText = MAIN.resources.getString(R.string.helper_text)
            finishHabitClass.ButtonProlong.visibility = VISIBLE
        }

        finishHabitClass.ButtonProlong.setOnClickListener {
            if(ValidateUseCase().validateName(finishHabitClass.NameGoalText, finishHabitClass.NameGoal) &&
                ValidateUseCase().validateNumber(finishHabitClass.NumberText, finishHabitClass.Number)){
                habit[0].title = finishHabitClass.NameGoalText.text.toString()
                habit[0].period = finishHabitClass.NumberText.text.toString().toInt()
                UpdateHabitUseCase().execute(habit[0], MAIN)

                dismiss()
            }
        }

        finishHabitClass.NameGoalText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase().validateName(finishHabitClass.NameGoalText, finishHabitClass.NameGoal)
            }
        })

        finishHabitClass.NumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase().validateNumber(finishHabitClass.NumberText, finishHabitClass.Number)
            }
        })
    }
}