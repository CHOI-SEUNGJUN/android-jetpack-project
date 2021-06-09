package c.june.learning.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM TODO ORDER BY created_at DESC")
    fun getTodoContents(): Flow<List<Todo>>

    @Insert
    suspend fun insertTodoContent(vararg todo: Todo)

    @Delete
    fun deleteTodoContent(todo: Todo)

    @Update
    fun updateTodoCheckState(todo: Todo)
}