package com.example.thetrackgoals.ui.fragments.updatehabit


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
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

class UpdateHabitItem : Fragment() {

//    private lateinit var binding: FragmentUpdateHabitItemBinding
//    private var title = ""
//    private var description = ""
//    private var drawableSelected = 0
//    private var timeStamp = ""
//
//    private lateinit var habitViewModel: HabitViewModel
//
//    private var day = 0
//    private var month = 0
//    private var year = 0
//    private var hour = 0
//    private var minute = 0
//
//    private var cleanDate = ""
//    private var cleanTime = ""
//
//    private val args by navArgs<UpdateHabitItemArgs>()
//
//    @RequiresApi(Build.VERSION_CODES.N)
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentUpdateHabitItemBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)
//
//        // Retrieve data from our habit list
//        binding.etHabitTitleUpdate.setText(args.selectedHabit.habit_title)
//        binding.etHabitDescriptionUpdate.setText(args.selectedHabit.habit_description)
//
//        // Other code remains mostly unchanged, use binding.viewId to access views
//
//        drawableSelected()
//        pickDateAndTime()
//
//        binding.btnConfirmUpdate.setOnClickListener {
//            updateHabit()
//        }
//
//        setHasOptionsMenu(true)
//    }
//
//
//    private fun updateHabit() {
//        //Get text from editTexts
//        title = binding.etHabitTitleUpdate.text.toString()
//        description = binding.etHabitDescriptionUpdate.text.toString()
//
//        //Create a timestamp string for our recyclerview
//        timeStamp = "$cleanDate $cleanTime"
//
//        //Check that the form is complete before submitting data to the database
//        if (!(title.isEmpty() || description.isEmpty() || timeStamp.isEmpty() || drawableSelected == 0)) {
//            val habit =
//                Habit(args.selectedHabit.id, title, description, timeStamp, drawableSelected)
//
//            //add the habit if all the fields are filled
//            habitViewModel.updateHabit(habit)
//            Toast.makeText(context, "Habit updated! successfully!", Toast.LENGTH_SHORT).show()
//
//            //navigate back to our home fragment
//            findNavController().navigate(R.id.action_updateHabitItem_to_habitList)
//        } else {
//            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//    private fun drawableSelected() {
//        binding.ivFastFoodSelectedUpdate.setOnClickListener {
//            binding.ivFastFoodSelectedUpdate.isSelected = !binding.ivFastFoodSelectedUpdate.isSelected
//            drawableSelected = R.drawable.ic_fastfood
//
//            //de-select the other options when we pick an image
//            binding.ivSmokingSelectedUpdate.isSelected = false
//            binding.ivTeaSelectedUpdate.isSelected = false
//        }
//
//        binding.ivSmokingSelectedUpdate.setOnClickListener {
//            binding.ivSmokingSelectedUpdate.isSelected = !binding.ivSmokingSelectedUpdate.isSelected
//            drawableSelected = R.drawable.ic_smoking2
//
//            //de-select the other options when we pick an image
//            binding.ivFastFoodSelectedUpdate.isSelected = false
//            binding.ivTeaSelectedUpdate.isSelected = false
//        }
//
//        binding.ivTeaSelectedUpdate.setOnClickListener {
//            binding.ivTeaSelectedUpdate.isSelected = !binding.ivTeaSelectedUpdate.isSelected
//            drawableSelected = R.drawable.ic_tea
//
//            //de-select the other options when we pick an image
//            binding.ivFastFoodSelectedUpdate.isSelected = false
//            binding.ivSmokingSelectedUpdate.isSelected = false
//        }
//
//    }
//
//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun pickDateAndTime() {
//        binding.btnPickDateUpdate.setOnClickListener {
//            // Create and show DatePickerDialog, set OnDateSetListener
//            val datePickerDialog = DatePickerDialog(
//                requireContext(),
//                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
//                    onDateSet(null, year, month, dayOfMonth)
//                },
//                year, month, day
//            )
//            datePickerDialog.show()
//        }
//
//        binding.btnPickTimeUpdate.setOnClickListener {
//            // Create and show TimePickerDialog, set OnTimeSetListener
//            val timePickerDialog = TimePickerDialog(
//                requireContext(),
//                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
//                    onTimeSet(null, hourOfDay, minute)
//                },
//                hour, minute, true
//            )
//            timePickerDialog.show()
//        }
//    }
//
//    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//        val cleanTime = Calculations.cleanTime(hourOfDay, minute)
//        binding.tvTimeSelectedUpdate.text = "Time: $cleanTime"
//    }
//
//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        val cleanDate = Calculations.cleanDate(dayOfMonth, month, year)
//        binding.tvDateSelectedUpdate.text = "Date: $cleanDate"
//    }
//
//    //get the current time
//    private fun getTimeCalendar() {
//        val cal = Calendar.getInstance()
//        hour = cal.get(Calendar.HOUR_OF_DAY)
//        minute = cal.get(Calendar.MINUTE)
//    }
//
//    //get the current date
//    private fun getDateCalendar() {
//        val cal = Calendar.getInstance()
//        day = cal.get(Calendar.DAY_OF_MONTH)
//        month = cal.get(Calendar.MONTH)
//        year = cal.get(Calendar.YEAR)
//    }
//
//    //Create options menu
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.single_item_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.nav_delete -> {
//                deleteHabit(args.selectedHabit)
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//    //------------------------------------------
//
//    //Delete a single Habit
//    private fun deleteHabit(habit: Habit) {
//        habitViewModel.deleteHabit(habit)
//        Toast.makeText(context, "Goals successfully deleted!", Toast.LENGTH_SHORT).show()
//
//        findNavController().navigate(R.id.action_updateHabitItem_to_habitList)
//    }
    //------------------------------------------

}

