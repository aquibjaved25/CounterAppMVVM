package com.assignment4.di

import com.assignment4.PaginationRepository
import com.assignment4.PaginationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideNetworkingRepository(impl: PaginationRepositoryImpl): PaginationRepository
}
