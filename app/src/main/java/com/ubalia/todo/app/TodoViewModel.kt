package com.ubalia.todo.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository): ViewModel() {

    val allTodos: LiveData<List<Todo>> = repository.allTodos.asLiveData()

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }

    fun insertAll(vararg todos: Todo) = viewModelScope.launch {
        repository.insertAll(*todos)
    }

    fun delete(todo: Todo) = viewModelScope.launch {
        repository.delete(todo)
    }

    fun delete(id: Int) = viewModelScope.launch {
        repository.delete(id)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class TodoViewModelFactory(private val repository: TodoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java) ) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}