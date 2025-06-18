package com.assignment3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class User(
   // @PrimaryKey(autoGenerate = true)
    @PrimaryKey
    val userid: Int ,
    val email: String,
    val fullName: String,
    val about: String,
)