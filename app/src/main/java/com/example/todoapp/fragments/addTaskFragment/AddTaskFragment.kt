package com.example.todoapp.fragments.addTaskFragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.fragments.viewModel.TaskViewModel
import com.example.todoapp.model.Task
import com.google.android.material.snackbar.Snackbar


class AddTaskFragment : Fragment() {

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get()=_binding!!

    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.btnAddTask.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val taskTitle = binding.etTaskTitle.text.toString()
        val taskDescription = binding.etTaskDescription.text.toString()

        if (inputCheck(taskTitle, taskDescription)){
            val task = Task(0, taskTitle, taskDescription)

            mTaskViewModel.addNewTask(task)
            Snackbar.make(requireView(), "Successfully added", Snackbar.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addTaskFragment_to_mainListFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(taskTitle: String, taskDescription: String): Boolean{
        return !(TextUtils.isEmpty(taskTitle) && TextUtils.isEmpty(taskDescription))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}