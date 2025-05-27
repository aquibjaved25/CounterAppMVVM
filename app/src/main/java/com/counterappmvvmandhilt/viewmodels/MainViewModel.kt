package com.counterappmvvmandhilt.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.counterappmvvmandhilt.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {
    val stateFlow: StateFlow<Int>
        get() = repository.stateFlow
    fun updateCount(){
        viewModelScope.launch {
            repository.setCount()
        }

    }

    fun resetCount(){
        viewModelScope.launch {
            repository.resetCount()
        }
    }
}
