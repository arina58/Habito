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
import com.example.habitstracker.R
import com.example.habitstracker.databinding.FinishGoalBinding
import com.example.habitstracker.di.DaggerAppComponent
import com.example.habitstracker.domain.model.HabitItem
import com.example.habitstracker.domain.useCase.DeleteHabitUseCase
import com.example.habitstracker.domain.useCase.GetHabitItemUseCase
import com.example.habitstracker.domain.useCase.UpdateHabitUseCase
import com.example.habitstracker.domain.useCase.ValidateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DialogFinishHabit: DialogFragment() {
    private lateinit var finishHabitClass : FinishGoalBinding

    @Inject
    lateinit var getHabitItemUseCase: GetHabitItemUseCase

    @Inject
    lateinit var deleteHabitUseCase: DeleteHabitUseCase

    @Inject
    lateinit var updateHabitUseCase: UpdateHabitUseCase

    private val scope = CoroutineScope(Dispatchers.IO)

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

        val component = DaggerAppComponent.factory().create(requireActivity().application, requireActivity().applicationContext)
        component.inject(this)

        val id = requireArguments().getInt("id", 0)
        var habit: HabitItem? = null
        scope.launch {
            habit = getHabitItemUseCase(id)

            finishHabitClass.NameGoalText.text = Editable.Factory.getInstance().newEditable(habit?.title)
            finishHabitClass.NumberText.text = Editable.Factory.getInstance().newEditable(habit?.period.toString())
        }


        finishHabitClass.ButtonFinish.setOnClickListener {
//            MAIN.vmHome.updateData(-1,
//                GetHabitsFromDBUseCase().execute(ID, arrayOf("$id"), MAIN)[0])
//            MAIN.vmHome.updateChart(-1)

            scope.launch {
                deleteHabitUseCase(id)
            }

            dismiss()
        }

        finishHabitClass.ButtonContinue.setOnClickListener {
            finishHabitClass.ButtonFinish.visibility = GONE
            finishHabitClass.ButtonContinue.visibility = GONE
            finishHabitClass.Number.visibility = VISIBLE
            finishHabitClass.NameGoal.isEnabled = true
            finishHabitClass.NameGoal.helperText = resources.getString(R.string.helper_text)
            finishHabitClass.ButtonProlong.visibility = VISIBLE
        }

        finishHabitClass.ButtonProlong.setOnClickListener {
            if(ValidateUseCase(requireActivity()).validateName(finishHabitClass.NameGoalText, finishHabitClass.NameGoal) &&
                ValidateUseCase(requireActivity()).validateNumber(finishHabitClass.NumberText, finishHabitClass.Number)){
                habit?.title = finishHabitClass.NameGoalText.text.toString()
                habit?.period = finishHabitClass.NumberText.text.toString().toInt()
                scope.launch {
                    habit?.let { updateHabitUseCase(it) }

                }

                dismiss()
            }
        }

        finishHabitClass.NameGoalText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase(requireActivity()).validateName(finishHabitClass.NameGoalText, finishHabitClass.NameGoal)
            }
        })

        finishHabitClass.NumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                ValidateUseCase(requireActivity()).validateNumber(finishHabitClass.NumberText, finishHabitClass.Number)
            }
        })
    }
}