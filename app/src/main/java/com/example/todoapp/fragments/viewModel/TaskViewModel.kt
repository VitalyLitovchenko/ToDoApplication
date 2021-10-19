package com.example.todoapp.fragments.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TaskDao
import com.example.todoapp.data.TaskDatabase
import com.example.todoapp.model.Task
import com.example.todoapp.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = TaskDatabase.getDataBase(
        application
    ).taskDao()
    private val repository: TaskRepository = TaskRepository(taskDao)
    val readAllData: LiveData<List<Task>> = repository.readAllData

//    val readAllData: LiveData<List<Task>>
//    private val repository: TaskRepository
//
//    init {
//        val taskDao = TaskDatabase.getDataBase(application).taskDao()
//        repository = TaskRepository(taskDao)
//        readAllData = repository.readAllData
//    }

    fun addNewTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNewTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(task)
        }
    }

    fun deleteCurrentTask(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCurrentTask(task)
        }
    }

    fun deleteAllTasks(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllTasks()
        }
    }
}