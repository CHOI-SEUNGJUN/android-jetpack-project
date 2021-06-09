package c.june.learning.data

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM TODO ORDER BY created_at DESC")
    suspend fun getTodoContents(): List<Todo>

    @Insert
    suspend fun insertTodoContent(vararg todo: Todo)

    @Delete
    fun deleteTodoContent(todo: Todo)

    @Update
    fun updateTodoCheckState(todo: Todo)
}