package c.june.learning.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import c.june.learning.data.GithubRepository
import c.june.learning.data.Repo
import kotlinx.coroutines.flow.Flow

class GithubViewModel(private val repository: GithubRepository): ViewModel() {
    private var queryString: String? = null
    var searchResult: Flow<PagingData<Repo>>? = null

    fun searchRepository(queryString: String): Flow<PagingData<Repo>> {
        val lastResult = searchResult
        if (this.queryString == queryString && lastResult != null) {
            return lastResult
        }
        this.queryString = queryString

        val newResult = repository.getRepositorySearchItems(queryString)
            .cachedIn(viewModelScope)
        searchResult = newResult
        return newResult
    }
}