package c.june.learning.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import c.june.learning.data.Todo
import c.june.learning.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository): ViewModel() {

    fun insertValue(memo: String) = viewModelScope.launch {
        repository.insertContent(
            Todo(content = memo)
        )
    }

    fun updateValue(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateContent(todo)
    }

    fun getContent(): Flow<PagingData<Todo>> {
        return repository.getTodoContentItemsByPaging()
            .cachedIn(viewModelScope)
    }
}