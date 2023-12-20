package com.example.roomwithmvvm.data.local.repository

import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeUserRepository : UserRepository {
    // List to keep track of method calls
    private val methodCalls = mutableListOf<RepositoryMethod>()


    private val users = mutableListOf<User>()

    private val observableUsers: MutableStateFlow<List<User>> =  MutableStateFlow<List<User>>(users)
    override suspend fun getAllData(): Flow<List<User>> {
        methodCalls.add(RepositoryMethod.GET_USERS)
        return observableUsers
    }

    override suspend fun updateUser(user: User) {
        val positionOfElementToReplace = (users.find { it.id == user.id }?.id)
        positionOfElementToReplace?.let {
            users.set(positionOfElementToReplace - 1, user)
        }
        refreshUsersFlow()
        methodCalls.add(RepositoryMethod.UPDATE_USER)
    }

    override suspend fun deleteUser(user: User) {
        users.remove(user)
        refreshUsersFlow()
        methodCalls.add(RepositoryMethod.DELETE_USER)
    }

    override suspend fun addUser(user: User) {
        users.add(user)
        refreshUsersFlow()
        methodCalls.add(RepositoryMethod.ADD_USER)
        var size = methodCalls.size

    }

    override suspend fun deleteAll() {
        users.clear()
        refreshUsersFlow()
        methodCalls.add(RepositoryMethod.DELETE_ALL_USERS)
    }

    private fun refreshUsersFlow(){
        observableUsers.value = users
    }

    // Function to check if a method was called
    fun hasMethodBeenCalled(methodName: RepositoryMethod): Boolean {
        return methodName in methodCalls
    }

//    private var shouldReturnNetworkError = false
//    fun setShouldReturnNetworkError(value: Boolean){
//    shouldReturnNetworkError = value
//    }

}