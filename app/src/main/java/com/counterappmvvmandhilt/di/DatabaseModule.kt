package com.counterappmvvmandhilt.di

import com.counterappmvvmandhilt.repository.IRepository
import com.counterappmvvmandhilt.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRepository(): IRepository{
        return RepositoryImpl()
    }

}