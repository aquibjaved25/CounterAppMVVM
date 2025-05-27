package com.counterappmvvmandhilt.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RepositoryImpl:IRepository {
    private var count = 0
    private val _stateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    override val stateFlow: StateFlow<Int>
        get() = _stateFlow.asStateFlow()

    override suspend fun setCount() {
        ++count
        _stateFlow.emit(count)
    }

    override suspend fun resetCount() {
        count = 0
        _stateFlow.emit(count)
    }
}