package com.assignment3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment3.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
