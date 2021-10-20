package com.example.todoapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todoapp.fragments.viewModel.TaskViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val mTaskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragmentContainerView).navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.delete_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDelete){
            deleteAllTasks()
        }
        return super.onOptionsItemSelected(item)
    }

    fun deleteAllTasks(){
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") {_, _ ->
            mTaskViewModel.deleteAllTasks()
            val view = findViewById<ConstraintLayout>(R.id.mainConstraint)
            Snackbar.make(view, "Successfully deleted", Snackbar.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No") {_, _ ->

        }
        builder.setTitle("Delete all Tasks ?")
        builder.setMessage("Are you sure want to delete all Tasks ?")
        builder.create().show()
    }
}