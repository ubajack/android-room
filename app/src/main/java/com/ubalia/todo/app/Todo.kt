package com.ubalia.todo.app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_todos")
data class Todo(
    @ColumnInfo(name = "task") val task: String = "TODO",
    @ColumnInfo(name = "status") val status: Boolean = false,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null,
)
