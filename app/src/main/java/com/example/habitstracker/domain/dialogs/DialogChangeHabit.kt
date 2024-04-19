package com.example.habitstracker.domain.dialogs

import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.habitstracker.ID
import com.example.habitstracker.MAIN
import com.example.habitstracker.databinding.ChangeGoalBinding
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase

class DialogChangeHabit: DialogFragment() {
    private lateinit var changeHabitClass: ChangeGoalBinding
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
        val id = arguments!!.getInt("id", 0)
        val item = GetHabitsFromDBUseCase().execute(ID, arrayOf("$id"), MAIN)

        changeHabitClass.NameGoal.text = Editable.Factory.getInstance().newEditable(item[0].title)
        changeHabitClass.editTextNumber.text = Editable.Factory.getInstance().newEditable(item[0].period.toString())
        changeHabitClass.description.text = Editable.Factory.getInstance().newEditable(item[0].description)

        changeHabitClass.ButtonCreate.setOnClickListener {
            if (changeHabitClass.NameGoal.text.toString().length in 1..50 &&
                changeHabitClass.editTextNumber.text.toString() != "" &&
                changeHabitClass.editTextNumber.text.toString().length < 4 &&
                changeHabitClass.editTextNumber.text.toString().toInt() in 2..365 &&
                changeHabitClass.description.text.length <= 100) {

                item[0].period = changeHabitClass.editTextNumber.text.toString().toInt()
                item[0].title = changeHabitClass.NameGoal.text.toString()
                item[0].description = changeHabitClass.description.text.toString()
                UpdateHabitUseCase().execute(item[0], MAIN)
                MAIN.vmHome.updateData(0, item[0])

                if(MAIN.vmAnalysis != null){
                    MAIN.vmAnalysis?.updateItem(item[0].id)
                }
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

        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.setCanceledOnTouchOutside(false)
    }
}