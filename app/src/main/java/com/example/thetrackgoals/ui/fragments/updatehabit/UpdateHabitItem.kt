package com.example.thetrackgoals.ui.fragments.updatehabit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thetrackgoals.R
import com.example.thetrackgoals.data.models.Habit
import com.example.thetrackgoals.databinding.FragmentUpdateHabitItemBinding
import com.example.thetrackgoals.logic.utils.Calculations
import com.example.thetrackgoals.ui.viewmodels.HabitViewModel
import java.util.Calendar

class UpdateHabitItem : Fragment(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private lateinit var binding: FragmentUpdateHabitItemBinding
    private lateinit var habitViewModel: HabitViewModel
    private var drawableSelected = 0
    private var timeStamp = ""
    private var cleanDate = ""
    private var cleanTime = ""

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private val args by navArgs<UpdateHabitItemArgs>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateHabitItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
        updateUI()
        binding.btnConfirmUpdate.setOnClickListener { updateHabit() }
        setHasOptionsMenu(true)
    }

    private fun updateUI() {
        args.selectedHabit?.let {
            binding.etHabitTitleUpdate.setText(it.habit_title.orEmpty())
            binding.etHabitDescriptionUpdate.setText(it.habit_description.orEmpty())
            drawableSelected()
            pickDateAndTime()
        }
    }

    private fun updateHabit() {
        val title = binding.etHabitTitleUpdate.text.toString()
        val description = binding.etHabitDescriptionUpdate.text.toString()
        timeStamp = "$cleanDate $cleanTime"

        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty() || drawableSelected == 0)) {
            val habit = Habit(args.selectedHabit.id, title, description, timeStamp, drawableSelected)
            habitViewModel.updateHabit(habit)
            Toast.makeText(requireContext(), "Goals updated successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateHabitItem_to_habitList)
        } else {
            Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun drawableSelected() {
        binding.ivFastFoodSelectedUpdate.setOnClickListener {
            binding.ivFastFoodSelectedUpdate.isSelected = !binding.ivFastFoodSelectedUpdate.isSelected
            drawableSelected = R.drawable.ic_fastfood
            deselectOtherOptions(binding.ivSmokingSelectedUpdate, binding.ivTeaSelectedUpdate)
        }

        binding.ivSmokingSelectedUpdate.setOnClickListener {
            binding.ivSmokingSelectedUpdate.isSelected = !binding.ivSmokingSelectedUpdate.isSelected
            drawableSelected = R.drawable.ic_smoking2
            deselectOtherOptions(binding.ivFastFoodSelectedUpdate, binding.ivTeaSelectedUpdate)
        }

        binding.ivTeaSelectedUpdate.setOnClickListener {
            binding.ivTeaSelectedUpdate.isSelected = !binding.ivTeaSelectedUpdate.isSelected
            drawableSelected = R.drawable.ic_tea
            deselectOtherOptions(binding.ivFastFoodSelectedUpdate, binding.ivSmokingSelectedUpdate)
        }
    }

    private fun deselectOtherOptions(vararg imageViews: View) {
        imageViews.forEach { it.isSelected = false }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun pickDateAndTime() {
        binding.btnPickDateUpdate.setOnClickListener {
            showDatePicker()
        }

        binding.btnPickTimeUpdate.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            this,
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            this,
            hour, minute, true
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        cleanTime = Calculations.cleanTime(hourOfDay, minute)
        binding?.tvTimeSelectedUpdate?.text = "Time: $cleanTime"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        cleanDate = Calculations.cleanDate(dayOfMonth, month, year)
        binding?.tvDateSelectedUpdate?.text = "Date: $cleanDate"
    }


    private fun getTimeCalendar() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.single_item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> deleteHabit(args.selectedHabit)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteHabit(habit: Habit?) {
        habit?.let {
            habitViewModel.deleteHabit(it)
            Toast.makeText(requireContext(), "Habit successfully deleted!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateHabitItem_to_habitList)
        }
    }
}