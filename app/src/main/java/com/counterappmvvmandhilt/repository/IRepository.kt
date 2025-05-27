package com.counterappmvvmandhilt.repository

import kotlinx.coroutines.flow.StateFlow

interface IRepository {
    val stateFlow: StateFlow<Int>
    suspend fun setCount( )
    suspend fun resetCount()
}