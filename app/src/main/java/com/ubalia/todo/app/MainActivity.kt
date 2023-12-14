package com.ubalia.todo.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoViewModel.allTodos.observe(this) {
            Log.d("MainActivity", "Saved todos : $it")
        }
    }
}