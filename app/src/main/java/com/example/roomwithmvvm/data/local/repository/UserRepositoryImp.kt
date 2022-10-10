package com.example.roomwithmvvm.data.local.repository

import com.example.roomwithmvvm.data.local.source.User
import com.example.roomwithmvvm.data.local.source.UserDao
import com.example.roomwithmvvm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val userDao: UserDao) : UserRepository{


    override suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    override suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    override suspend fun deleteAll(){
        userDao.deleteAll()
    }

    override suspend fun deleteUser(user: User){
        userDao.delete(user)
    }

    override suspend fun getAllData(): Flow<List<User>> {
        return userDao.readAllData()

    }
}