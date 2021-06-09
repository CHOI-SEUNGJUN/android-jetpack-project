package c.june.learning.data

class TodoRepository(private val todoDao: TodoDao) {

    suspend fun getTodoContent(): List<Todo> {
        return todoDao.getTodoContents()
    }

    suspend fun insertContent(todo: Todo) {
        todoDao.insertTodoContent(todo)
    }
}