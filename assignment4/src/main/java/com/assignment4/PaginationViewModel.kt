package com.assignment4

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaginationViewModel @Inject constructor(
    private val repository: PaginationRepository
) : ViewModel() {
    private val _repositories = MutableStateFlow<PagingData<RepositoryData>>(PagingData.empty())
    val repositories: StateFlow<PagingData<RepositoryData>> get() = _repositories

    init {

        searchRepositories()


        viewModelScope.launch {     repositories.collect {        it.map { Log.d("TAG", it.id) }    }}

    }

    fun searchRepositories() {
        viewModelScope.launch {
            repository.searchRepositories().collect {
                _repositories.value = it
            }
        }
    }
}
