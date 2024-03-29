package com.example.todoapp.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.TaskDao
import com.example.todoapp.model.Task
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addNewTask(task: Task){
        taskDao.addNewTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteCurrentTask(task: Task){
        taskDao.deleteCurrentTask(task)
    }

    suspend fun deleteAllTasks(){
        taskDao.deleteAllTasks()
    }

}