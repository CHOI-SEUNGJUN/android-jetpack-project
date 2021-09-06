package c.june.learning.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import c.june.learning.data.Todo
import c.june.learning.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
    ): ViewModel() {

    fun insertValue(memo: String) = viewModelScope.launch {
        repository.insertContent(
            Todo(content = memo)
        )
    }

    fun updateValue(todo: Todo) = viewModelScope.launch {
        repository.updateContent(todo)
    }

    fun getContent(): Flow<PagingData<Todo>> {
        return repository.getTodoContentItemsByPaging()
            .cachedIn(viewModelScope)
    }
}