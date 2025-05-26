package com.counterappmvvmandhilt.repository

interface IRepository {
    suspend fun setCount( )
     fun getCount( ) :Int
    suspend fun resetCount()
}