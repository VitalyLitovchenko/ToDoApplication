package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteCurrentTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()
}