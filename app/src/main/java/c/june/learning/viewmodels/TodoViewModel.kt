package c.june.learning.viewmodels

import androidx.lifecycle.*
import c.june.learning.data.Todo
import c.june.learning.data.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository): ViewModel() {

    val contents: LiveData<List<Todo>> = repository.allContents.asLiveData()

    fun setTestValue() = viewModelScope.launch {
        repository.insertContent(
            Todo(content = "test")
        )
    }
}