package com.counterappmvvmandhilt.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.counterappmvvmandhilt.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {

    fun updateCount(){
        viewModelScope.launch {
            repository.setCount()
        }

    }

    fun getCount():Int{
        return repository.getCount()

    }

    fun resetCount(){
        viewModelScope.launch {
            repository.resetCount()
        }
    }
}
