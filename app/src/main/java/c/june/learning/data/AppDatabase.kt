package c.june.learning.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getTodoDao(): TodoDao
}