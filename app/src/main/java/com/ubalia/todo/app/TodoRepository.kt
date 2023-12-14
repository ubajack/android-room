package com.ubalia.todo.app

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: Flow<List<Todo>> = todoDao.getAll()

    @WorkerThread
    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    @WorkerThread
    suspend fun insertAll(vararg todos: Todo) {
        todoDao.insertAll(*todos)
    }

    @WorkerThread
    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }

    @WorkerThread
    suspend fun delete(id: Int) {
        todoDao.delete(id)
    }

    @WorkerThread
    suspend fun deleteAll() {
        todoDao.deleteAll()
    }
}