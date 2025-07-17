package com.assignment4

import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

class RepositoryPagingSource @Inject constructor(
    private val api: PicSumApi
) : PagingSource<Int, RepositoryData>() {
    override fun getRefreshKey(state: PagingState<Int, RepositoryData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryData> {
        val currentPageNumber = params.key ?: 1

        return try {
            val response = api.searchRepositories(
                pageNumber = currentPageNumber,
                pageSize = params.loadSize
            )

            val repositories = response

            LoadResult.Page(
                data = repositories,
                prevKey = if (currentPageNumber == 1) null else currentPageNumber - 1,
                nextKey = if (repositories.isEmpty()) null else currentPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}