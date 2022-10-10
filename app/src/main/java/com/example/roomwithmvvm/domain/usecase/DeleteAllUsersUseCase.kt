package com.example.roomwithmvvm.domain.usecase

import com.example.roomwithmvvm.domain.repository.UserRepository
import javax.inject.Inject

class DeleteAllUsersUseCase @Inject constructor(
    private val userRep: UserRepository
) {
    suspend fun deleteAllUsers(){
        userRep.deleteAll()
    }
}