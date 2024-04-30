package com.example.habitstracker.domain.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.habitstracker.ID
import com.example.habitstracker.MAIN
import com.example.habitstracker.databinding.ChangeGoalBinding
import com.example.habitstracker.domain.useCase.GetHabitsFromDBUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import com.example.habitstracker.domain.useCase.ValidateUseCase

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

        changeHabitClass.NameGoalText.text = Editable.Factory.getInstance().newEditable(item[0].title)
        changeHabitClass.NumberText.text = Editable.Factory.getInstance().newEditable(item[0].period.toString())
        changeHabitClass.DescriptionText.text = Editable.Factory.getInstance().newEditable(item[0].description)

        changeHabitClass.NameGoalText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase().validateName(changeHabitClass.NameGoalText, changeHabitClass.NameGoal)
            }

        })

        changeHabitClass.DescriptionText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase().validateDescription(changeHabitClass.DescriptionText, changeHabitClass.Description)
            }
        })

        changeHabitClass.NumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase().validateNumber(changeHabitClass.NumberText, changeHabitClass.Number)
            }
        })

        changeHabitClass.ButtonCreate.setOnClickListener {
            if (ValidateUseCase().validateName(changeHabitClass.NameGoalText, changeHabitClass.NameGoal) &&
                ValidateUseCase().validateNumber(changeHabitClass.NumberText, changeHabitClass.Number) &&
                ValidateUseCase().validateDescription(changeHabitClass.DescriptionText, changeHabitClass.Description)) {

                item[0].period = changeHabitClass.NumberText.text.toString().toInt()
                item[0].title = changeHabitClass.NameGoalText.text.toString()
                item[0].description = changeHabitClass.DescriptionText.text.toString()
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

        dialog?.window?.attributes?.width = (resources.displayMetrics.widthPixels - 40)

        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

