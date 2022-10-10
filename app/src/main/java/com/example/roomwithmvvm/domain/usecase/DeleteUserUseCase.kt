package com.example.roomwithmvvm.domain.usecase

import com.example.roomwithmvvm.data.local.repository.UserRepositoryImp
import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRep: UserRepository
) {
    suspend fun deleteUser(user: User){
        userRep.deleteUser(user)
    }
}