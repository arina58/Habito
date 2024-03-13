package com.example.habitstracker

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.Gravity.CENTER
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.DialogFragment
import com.example.habitstracker.databinding.WindowCompletedHabitBinding
import com.example.habitstracker.domain.useCase.DeleteHabitUseCase
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase

class DialogFinishHabit: DialogFragment() {
    private lateinit var finishHabitClass : WindowCompletedHabitBinding

    interface OnDialogDismissListener {
        fun onDialogDismissed()
    }

    private var dismissListener: OnDialogDismissListener? = null

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

        dialog?.window?.setGravity(CENTER)
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        finishHabitClass = WindowCompletedHabitBinding.inflate(layoutInflater, container, false)

        return finishHabitClass.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.setCancelable(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments!!.getInt("id", 0)
        var habit = GetHabitsFromDBUseCase().execute(ID, arrayOf("$id"))

        finishHabitClass.NameGoal.text = habit[0].title

        finishHabitClass.ButtonFinish.setOnClickListener {
            DeleteHabitUseCase().execute(id)
            dismiss()
        }

        finishHabitClass.ButtonContinue.setOnClickListener {
            finishHabitClass.ButtonFinish.visibility = GONE
            finishHabitClass.ButtonContinue.visibility = GONE
            finishHabitClass.LinearPeriod.visibility = VISIBLE
            finishHabitClass.ButtonProlong.visibility = VISIBLE
        }

        finishHabitClass.ButtonProlong.setOnClickListener {
            if(finishHabitClass.editTextNumber.text.toString().toInt() in 2..365){
                habit[0].period = finishHabitClass.editTextNumber.text.toString().toInt()
                UpdateHabitUseCase().execute(habit[0])

                dismiss()
            }
        }
    }

    fun setOnDialogDismissListener(listener: OnDialogDismissListener) {
        dismissListener = listener
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.onDialogDismissed()
    }
}