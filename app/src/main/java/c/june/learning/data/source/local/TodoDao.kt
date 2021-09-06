package c.june.learning.data.source.local

import androidx.room.*
import c.june.learning.data.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM TODO ORDER BY created_at DESC LIMIT :loadSize OFFSET (:page-1) * :loadSize")
    suspend fun getTodoContentsByPaging(page: Int, loadSize: Int): List<Todo>

    @Insert
    suspend fun insertTodoContent(vararg todo: Todo)

    @Delete
    fun deleteTodoContent(todo: Todo)

    @Update
    fun updateTodoCheckState(todo: Todo)
}