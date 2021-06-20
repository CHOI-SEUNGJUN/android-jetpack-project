package c.june.learning.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import c.june.learning.api.GithubService
import java.lang.Exception

class GithubPagingSource(
    private val service: GithubService,
    private val query: String
): PagingSource<Int, Repo>() {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val page = params.key ?: 1
        val apiQuery = query + IN_QUALIFIER
        return try {
            val response = service.searchRepos(apiQuery, page, params.loadSize)
            val items = response.items
            val nextKey = if (items.isEmpty()) {
                null
            } else {
                page + (params.loadSize / 10)
            }
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val IN_QUALIFIER = "in:name,description"
    }
}