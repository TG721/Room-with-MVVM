package com.example.roomwithmvvm.domain.usecase

import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRep: UserRepository
) {
    suspend fun getAllUsers(): Flow<List<User>> {
        return userRep.getAllData()
    }
}