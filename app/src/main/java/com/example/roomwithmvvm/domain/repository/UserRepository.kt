package com.example.roomwithmvvm.domain.repository

import com.example.roomwithmvvm.data.local.source.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAllData(): Flow<List<User>>
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    suspend fun addUser(user: User)
    suspend fun deleteAll()
}