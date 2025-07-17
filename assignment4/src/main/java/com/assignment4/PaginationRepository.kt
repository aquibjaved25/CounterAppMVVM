package com.assignment4

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PaginationRepository {
    suspend fun searchRepositories(): Flow<PagingData<RepositoryData>>
}

class PaginationRepositoryImpl @Inject constructor(
    private val api: PicSumApi
) : PaginationRepository {
//    override suspend fun searchRepositories(query: String): List<Repository> {
//        return api.searchRepositories(query).items
//    }

    override suspend fun searchRepositories(): Flow<PagingData<RepositoryData>> {
        val pageSize = 10
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                initialLoadSize = 2 * pageSize,
                prefetchDistance = 10
            )
        ) {
            RepositoryPagingSource( api = api)
        }.flow
    }
}
