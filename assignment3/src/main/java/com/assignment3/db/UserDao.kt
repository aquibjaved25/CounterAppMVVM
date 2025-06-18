package com.assignment3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.assignment3.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    //    @Query("INSERT INTO User values (user.id, user.name)")
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // One-shot write query
    @Insert // Update + Insert
    suspend fun insertUser(user: User)

    // One-shot read query


//    @Query("DELETE FROM User WHERE id = :userId")
//    suspend fun deleteUser(userId: Int)

    @Delete
    suspend fun deleteUser(user: User)

    // Observable read query
    @Query("SELECT * From User")
    fun getAllUsersFlow(): Flow<List<User>>
}
