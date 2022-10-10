package com.example.roomwithmvvm.di

import com.example.roomwithmvvm.data.local.repository.UserRepositoryImp
import com.example.roomwithmvvm.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImp
    ) : UserRepository

}