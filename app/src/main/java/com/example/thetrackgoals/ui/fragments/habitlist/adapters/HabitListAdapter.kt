package com.example.thetrackgoals.ui.fragments.habitlist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.thetrackgoals.data.models.Habit
import com.example.thetrackgoals.databinding.RecyclerHabitItemBinding
import com.example.thetrackgoals.logic.utils.Calculations
import com.example.thetrackgoals.ui.fragments.habitlist.HabitListDirections

class HabitListAdapter : RecyclerView.Adapter<HabitListAdapter.MyViewHolder>() {

    var habitsList = emptyList<Habit>()

    inner class MyViewHolder(private val binding: RecyclerHabitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cvCardView.setOnClickListener {
                val position = adapterPosition
                Log.d("HabitsListAdapter", "Item clicked at: $position")

//                habitsList[position]

                val action =
                    HabitListDirections.actionHabitListToUpdateHabitItem()
                binding.root.findNavController().navigate(action)
            }
        }

        fun bind(habit: Habit) {
            binding.ivHabitIcon.setImageResource(habit.imageId)
            binding.tvItemDescription.text = habit.habit_description
            binding.tvTimeElapsed.text =
                Calculations.calculateTimeBetweenDates(habit.habit_startTime)
            binding.tvItemCreatedTimeStamp.text = "Since: ${habit.habit_startTime}"
            binding.tvItemTitle.text = habit.habit_title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerHabitItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(habitsList[position])
    }

    override fun getItemCount(): Int {
        return habitsList.size
    }

    fun setData(habit: List<Habit>) {
        this.habitsList = habit
        notifyDataSetChanged()
    }
}
