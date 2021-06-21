package c.june.learning.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    suspend fun insertContent(todo: Todo) {
        todoDao.insertTodoContent(todo)
    }

    suspend fun updateContent(todo: Todo) {
        todoDao.updateTodoCheckState(todo)
    }

    fun getTodoContentItemsByPaging(): Flow<PagingData<Todo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TodoPagingSource(todoDao) }
        ).flow
    }
}