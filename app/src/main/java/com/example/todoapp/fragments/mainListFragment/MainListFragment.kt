package com.example.todoapp.fragments.mainListFragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.adapter.TaskAdapter
import com.example.todoapp.databinding.FragmentMainListBinding
import com.example.todoapp.fragments.viewModel.TaskViewModel
import com.google.android.material.snackbar.Snackbar
import java.nio.file.attribute.AclEntry


class MainListFragment : Fragment() {

    private var _binding: FragmentMainListBinding? = null
    private val binding get()=_binding!!

    private val mTaskViewModel: TaskViewModel by viewModels()

//    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        mTaskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        val adapter = TaskAdapter()
        binding.mainRecyclerView.adapter = adapter

        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer { task ->
            adapter.setData(task)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainListFragment_to_addTaskFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
