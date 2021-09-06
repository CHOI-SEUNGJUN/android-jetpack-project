package c.june.learning.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import c.june.learning.api.GithubService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepository @Inject constructor(private val service: GithubService) {
    fun getRepositorySearchItems(query: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubPagingSource(service, query) }
        ).flow
    }
}