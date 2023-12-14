package com.ubalia.todo.app

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM app_todos ORDER BY id")
    fun getAll(): Flow<List<Todo>>

    @Insert
    suspend fun insert(todo: Todo)

    @Insert
    suspend fun insertAll(vararg todos: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("DELETE FROM app_todos WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM app_todos")
    suspend fun deleteAll()

}