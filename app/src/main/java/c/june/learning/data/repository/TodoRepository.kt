package c.june.learning.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import c.june.learning.data.model.Todo
import c.june.learning.data.source.TodoPagingSource
import c.june.learning.data.source.local.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    suspend fun insertContent(todo: Todo) = withContext(Dispatchers.IO) {
        todoDao.insertTodoContent(todo)
    }

    suspend fun updateContent(todo: Todo) = withContext(Dispatchers.IO) {
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