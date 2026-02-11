package com.ravecodesolutions.rickandmorty.di

import com.ravecodesolutions.rickandmorty.data.Repository
import com.ravecodesolutions.rickandmorty.data.RepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepository(repositoryImp: RepositoryImp): Repository {
        return repositoryImp
    }
}