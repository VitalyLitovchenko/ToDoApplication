package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.fragments.mainListFragment.MainListFragmentDirections
import com.example.todoapp.model.Task

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var taskList = emptyList<Task>()

    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.mainLinearLayout)
        val taskTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val taskDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.taskTitle.text = currentItem.title
        holder.taskDescription.text = currentItem.description

        holder.linearLayout.setOnClickListener {
            val action = MainListFragmentDirections.actionMainListFragmentToUpdateFragment()
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }
}
