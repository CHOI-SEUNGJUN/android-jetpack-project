package c.june.learning.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import c.june.learning.data.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getTodoDao(): TodoDao
}