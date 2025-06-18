package com.assignment3.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment3.model.User
import com.assignment3.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
var i: Int = 1
    val usersFlow: StateFlow<List<User>> = userRepository.getAllUsersFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.Eagerly,
            initialValue = emptyList()
        )

    fun addUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val user = User(
                userid = i++,
                fullName = "Aquib Javed",
                email = "aquib.javed@gmail.com",
                about = "about myself"
            )
            userRepository.insertUser(user)
            onSuccess()
        }
    }

    fun deleteUser(user: User, onSuccess: () -> Unit) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
            onSuccess()
        }
    }

    fun setIValue(usersList: List<User>){
        var maxId = 1;
        usersList.forEach { user ->
            if (maxId<user.userid) {
                maxId = user.userid
            }
        }
        i =maxId+1
    }
}