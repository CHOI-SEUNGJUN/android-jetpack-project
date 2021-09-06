package c.june.learning.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import c.june.learning.data.model.Todo
import c.june.learning.data.source.local.TodoDao

class TodoPagingSource(
    private val dao: TodoDao
): PagingSource<Int, Todo>() {
    override fun getRefreshKey(state: PagingState<Int, Todo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Todo> {
        val page = params.key ?: 1
        return try {
            val items = dao.getTodoContentsByPaging(page, params.loadSize)
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + (params.loadSize / 10)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}