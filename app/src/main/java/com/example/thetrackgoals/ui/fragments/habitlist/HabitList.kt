package com.example.thetrackgoals.ui.fragments.habitlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thetrackgoals.R
import com.example.thetrackgoals.data.models.Habit
import com.example.thetrackgoals.databinding.FragmentHabitListBinding
import com.example.thetrackgoals.ui.fragments.habitlist.adapters.HabitListAdapter
import com.example.thetrackgoals.ui.viewmodels.HabitViewModel

class HabitList : Fragment() {
    private lateinit var binding: FragmentHabitListBinding
    private lateinit var habitList: List<Habit>
    private lateinit var habitViewModel: HabitViewModel
    private lateinit var adapter: HabitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HabitListAdapter()
        binding.rvHabits.adapter = adapter
        binding.rvHabits.layoutManager = LinearLayoutManager(context)

        viewModels()

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_habitList_to_createHabitItem)
        }

        setHasOptionsMenu(true)

        binding.swipeToRefresh.setOnRefreshListener {
            adapter.setData(habitList)
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun viewModels() {
        habitViewModel = ViewModelProvider(this).get(HabitViewModel::class.java)

        habitViewModel.getAllHabits.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            habitList = it

            if (it.isEmpty()) {
                binding.rvHabits.visibility = View.GONE
                binding.tvEmptyView.visibility = View.VISIBLE
            } else {
                binding.rvHabits.visibility = View.VISIBLE
                binding.tvEmptyView.visibility = View.GONE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> habitViewModel.deleteAllHabits()
        }
        return super.onOptionsItemSelected(item)
    }

}
