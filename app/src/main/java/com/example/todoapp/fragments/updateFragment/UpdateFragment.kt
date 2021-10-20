package com.example.todoapp.fragments.updateFragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentUpdateBinding
import com.example.todoapp.fragments.viewModel.TaskViewModel
import com.example.todoapp.model.Task
import com.google.android.material.snackbar.Snackbar

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get()= _binding!!

    private val mTaskViewModel: TaskViewModel by viewModels()

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etTaskTitleUpdate.setText(args.task.title)
        binding.etTaskDescriptionUpdate.setText(args.task.description)


        binding.btnUpdateTask.setOnClickListener {
            updateTask()
        }

        binding.btnDeleteTask.setOnClickListener {
            deleteCurrentTask()
        }
        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun updateTask(){
        val taskTitle = binding.etTaskTitleUpdate.text.toString()
        val taskDescription = binding.etTaskDescriptionUpdate.text.toString()

        if (inputChek(taskTitle, taskDescription)){
            val task = Task(args.task.id, taskTitle, taskDescription)

            mTaskViewModel.updateTask(task)

            findNavController().navigate(R.id.action_updateFragment_to_mainListFragment)

            Snackbar.make(requireView(), "Successfully updated", Snackbar.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputChek(taskTitle: String, taskDescription: String): Boolean{
        return !(TextUtils.isEmpty(taskTitle)&&TextUtils.isEmpty(taskDescription))
    }

    private fun deleteCurrentTask(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mTaskViewModel.deleteCurrentTask(args.task)
            Snackbar.make(requireView(), "Successfully deleted", Snackbar.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_mainListFragment)
        }
        builder.setNegativeButton("No") {_, _ ->

        }

        builder.setTitle("Delete ${args.task.title}?")
        builder.setMessage("Are you sure want to delete ${args.task.title}?")
        builder.create().show()
    }
}