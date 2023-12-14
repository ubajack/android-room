package com.ubalia.todo.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Todo::class], version = 1)
abstract class TodoRoomDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDao

    private class TodoDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    populateDatabase(it.todoDao())
                }
            }
        }

        suspend fun populateDatabase(todoDao: TodoDao) {
            todoDao.deleteAll()

            var todo = Todo("Demo")
            todoDao.insert(todo)
            todo = Todo("Test")
            todoDao.insert(todo)

            todoDao.insertAll(
                Todo("First"),
                Todo("Second"),
                Todo("Third")
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TodoRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TodoRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoRoomDatabase::class.java,
                    "todo_app_db"
                )
                    .addCallback(TodoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}