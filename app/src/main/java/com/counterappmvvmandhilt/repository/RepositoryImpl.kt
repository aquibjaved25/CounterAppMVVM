package com.counterappmvvmandhilt.repository

class RepositoryImpl:IRepository {
    private var count = 0
    override suspend fun setCount() {
        ++count
    }

    override  fun getCount(): Int {
        return count
    }

    override suspend fun resetCount() {
        count = 0
    }
}