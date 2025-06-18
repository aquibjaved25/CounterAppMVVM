package com.assignment3.repo

import com.assignment3.db.UserDatabase
import com.assignment3.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDatabase: UserDatabase
) {
    suspend fun insertUser(user: User) {
        userDatabase.getUserDao().insertUser(user)
    }



    suspend fun deleteUser(user: User) {
        userDatabase.getUserDao().deleteUser(user)
    }

    fun getAllUsersFlow(): Flow<List<User>> {
        return userDatabase.getUserDao().getAllUsersFlow()
    }
}