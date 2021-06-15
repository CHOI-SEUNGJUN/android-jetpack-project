package c.june.learning.data

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    val allContents: Flow<List<Todo>> = todoDao.getTodoContents()

    suspend fun insertContent(todo: Todo) {
        todoDao.insertTodoContent(todo)
    }

    suspend fun updateContent(todo: Todo) {
        todoDao.updateTodoCheckState(todo)
    }
}